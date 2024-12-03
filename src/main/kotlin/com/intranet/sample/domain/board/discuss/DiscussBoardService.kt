package com.intranet.sample.domain.board.discuss

import com.intranet.sample.domain.board.AbstractBoardService
import org.springframework.data.domain.Page
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class DiscussBoardService(
    override val repo: DiscussBoardRepository,
    override val passwordEncoder: PasswordEncoder
) : AbstractBoardService<DiscussBoard, DiscussBoardDTO.Info, DiscussBoardDTO.PageInfo>() {

    override fun create() = DiscussBoard()
    override fun toInfo(board: DiscussBoard) = DiscussBoardDTO.Info(board)
    override fun toPage(page: Page<DiscussBoard>) = DiscussBoardDTO.PageInfo(page)

    fun getAllComment(boardId: Long): List<DiscussBoardDTO.CommentInfo> {
        val comments = repo.findById(boardId)?.comments
            ?: throw IllegalArgumentException()

        return comments.map { DiscussBoardDTO.CommentInfo(it) }
    }

    fun getComment(boardId: Long, id: Long) =
        repo.findComment(boardId, id)?.let { DiscussBoardDTO.CommentInfo(it) }
            ?: throw IllegalArgumentException()

    @Transactional
    fun saveComment(boardId: Long, username: String, password: String, content: String): DiscussBoardDTO.CommentInfo {
        val board = repo.findById(boardId) ?: throw IllegalArgumentException()
        if (board.audit.isDeleted()) throw IllegalArgumentException()

        val comment = DiscussComment(
            board = board,
            username = username,
            password = passwordEncoder.encode(password),
            content = content
        )

        return repo.saveComment(comment)
            .let { DiscussBoardDTO.CommentInfo(it) }
    }

    @Transactional
    fun updateComment(boardId: Long, id: Long, password: String, content: String): DiscussBoardDTO.CommentInfo {
        val comment = repo.findComment(boardId, id) ?: throw IllegalArgumentException()

        comment.validatePassword(passwordEncoder, password)
        comment.write(content)

        return repo.saveComment(comment)
            .let { DiscussBoardDTO.CommentInfo(it) }
    }

    fun deleteComment(boardId: Long, id: Long, password: String) {
        val comment = repo.findComment(boardId, id) ?: throw IllegalArgumentException()

        comment.validatePassword(passwordEncoder, password)

        return repo.deleteComment(comment)
    }

}