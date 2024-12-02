package com.intranet.sample.domain.board

import org.springframework.data.domain.PageRequest
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class BoardService(
    private val repo: BoardRepository,
    private val passwordEncoder: PasswordEncoder
) {

    fun getDetail(id: Long) =
        repo.findById(id)?.let { BoardDTO.Info(it) }
            ?: throw IllegalArgumentException()

    fun getPage(request: PageRequest) =
        repo.findAllNotDeleted(request).let { BoardDTO.PageableSimpleInfo(it) }

    fun create(username: String, password: String, title: String, content: String): BoardDTO.Info {
        val encoded = passwordEncoder.encode(password)
        val board = Board()

        board.setUser(username, encoded)
        board.write(title, content)

        return repo.save(board)
            .let { BoardDTO.Info(it) }
    }

    fun update(id: Long, password: String, title: String, content: String) {
        val board = repo.findById(id) ?: throw IllegalArgumentException()

        if (!passwordEncoder.matches(password, board.getPassword()))
            throw IllegalArgumentException()

        board.write(title, content)

        return repo.save(board)
            .let { BoardDTO.Info(it) }
    }

    fun delete(id: Long, password: String) {
        val board = repo.findById(id) ?: throw IllegalArgumentException()

        if (!passwordEncoder.matches(password, board.getPassword()))
            throw IllegalArgumentException()

        repo.delete(board)
    }

}