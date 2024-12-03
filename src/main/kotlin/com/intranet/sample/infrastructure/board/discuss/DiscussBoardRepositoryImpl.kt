package com.intranet.sample.infrastructure.board.discuss

import com.intranet.sample.domain.board.discuss.DiscussBoard
import com.intranet.sample.domain.board.discuss.DiscussBoardRepository
import com.intranet.sample.domain.board.discuss.DiscussComment
import com.intranet.sample.infrastructure.board.AbstractBoardRepositoryImpl
import com.intranet.sample.infrastructure.board.DefaultBoardJpaRepository
import org.springframework.stereotype.Repository

@Repository
class DiscussBoardRepositoryImpl(
    override val jpa: DefaultBoardJpaRepository<DiscussBoard>,
    private val commentJpa: DiscussCommentJpaRepository,
) : AbstractBoardRepositoryImpl<DiscussBoard>(), DiscussBoardRepository {

    override fun findAllComments(boardId: Long): List<DiscussComment> =
        commentJpa.findAllByBoardId(boardId)

    override fun findAllComments(board: DiscussBoard): List<DiscussComment> =
        commentJpa.findAllByBoardId(board.id)

    override fun findComment(boardId: Long, commentId: Long): DiscussComment? =
        commentJpa.findByBoardIdAndId(boardId, commentId)

    override fun saveComment(comment: DiscussComment): DiscussComment =
        commentJpa.save(comment)

    override fun deleteComment(comment: DiscussComment) =
        commentJpa.delete(comment)

}