package com.intranet.sample.asis.domain.cug.board

import com.intranet.sample.configuration.exception.BizException
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BoardService(private val repo: BoardRepository) {

    @Transactional
    fun getDetail(id: Int) = repo.selectOne(id)
        ?: throw IllegalArgumentException("그런 글 없음")

    fun getPage(request: PageRequest) = repo.selectPage(request)

    @Transactional
    fun create(request: BoardDTO.Command): Board {
        val board = repo.create(
            Board(
                userName = request.userName,
                userEmail = request.userEmail,
                title = request.title,
                content = request.content,
                password = request.password,
            )
        )

        return board ?: throw BizException(HttpStatus.INTERNAL_SERVER_ERROR, "저장 실패 ㅠ")
    }

    @Transactional
    fun update(id: Int, request: BoardDTO.Command): Board {
        val board = repo.selectOne(id)?.also { it.validatePassword(request.password) }
            ?: throw BizException(HttpStatus.NOT_FOUND, "그런 글 없음")

        board.update(
            userName = request.userName,
            password = request.password,
            userEmail = request.userEmail,
            userHomepage = request.userHomepage,
            title = request.title,
            content = request.content
        )

        return repo.update(board).let {
            if (it < 1) throw BizException(HttpStatus.INTERNAL_SERVER_ERROR, "업데이트가 가끔 이상한 이유는???")
            else repo.selectOne(id)!!
        }
    }

    @Transactional
    fun delete(id: Int, password: String) {
        val board =
            repo.selectOne(id)?.also { it.validatePassword(password) } ?: throw BizException(
                HttpStatus.NOT_FOUND,
                "그런 글 없음"
            )

        repo.delete(board.id)
    }

}