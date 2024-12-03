package com.intranet.sample.domain.board

import com.intranet.sample.configuration.exception.BizException
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
abstract class AbstractBoardService<T : AbstractBoard, I : BoardDTO.OpenInfo<T>, P : BoardDTO.OpenPageInfo<T, I>> {

    protected abstract val repo: AbstractBoardRepository<T>
    protected abstract val passwordEncoder: PasswordEncoder

    protected abstract fun create(): T
    protected abstract fun toInfo(board: T): I
    protected abstract fun toPage(page: Page<T>): P

    fun getDetail(id: Long): I {
        val board = repo.findById(id) ?: throw BizException(BoardError.NOT_FOUND)

        if (board.audit.deletedAt != null) throw BizException(BoardError.ALREADY_DELETED)

        return toInfo(board)
    }

    fun getPage(request: PageRequest): P {
        val page = repo.findAllNotDeleted(request)
        return toPage(page)
    }

    @Transactional
    fun save(username: String, password: String, title: String, content: String): I {
        val encoded = passwordEncoder.encode(password)
        val board = create()

        board.setUser(username, encoded)
        board.write(title, content)

        return toInfo(repo.save(board))
    }

    @Transactional
    fun update(id: Long, password: String, title: String, content: String): I {
        val board = repo.findById(id) ?: throw BizException(BoardError.NOT_FOUND)

        if (!passwordEncoder.matches(password, board.getPassword()))
            throw BizException(BoardError.WRONG_PASSWORD)

        board.write(title, content)

        return toInfo(repo.save(board))
    }

    @Transactional
    fun delete(id: Long, password: String) {
        val board = repo.findById(id) ?: throw BizException(BoardError.NOT_FOUND)

        if (!passwordEncoder.matches(password, board.getPassword()))
            throw BizException(BoardError.WRONG_PASSWORD)

        repo.delete(board)
    }
}