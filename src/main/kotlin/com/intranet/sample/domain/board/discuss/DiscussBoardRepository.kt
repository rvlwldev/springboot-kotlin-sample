package com.intranet.sample.domain.board.discuss

import com.intranet.sample.domain.board.AbstractBoardRepository

interface DiscussBoardRepository : AbstractBoardRepository<DiscussBoard> {

    fun findAllComments(boardId: Long): List<DiscussComment>
    fun findAllComments(board: DiscussBoard): List<DiscussComment>
    fun findComment(commentId: Long): DiscussComment?
    fun saveComment(comment: DiscussComment): DiscussComment
    fun deleteComment(comment: DiscussComment)

}