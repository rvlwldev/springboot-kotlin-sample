package com.intranet.sample.infrastructure.board.discuss

import com.intranet.sample.domain.board.discuss.DiscussComment
import org.springframework.data.jpa.repository.JpaRepository

interface DiscussCommentJpaRepository : JpaRepository<DiscussComment, Long> {

    fun findAllByBoardId(boardId: Long): List<DiscussComment>
    fun findByBoardIdAndId(boardId: Long, commentId: Long): DiscussComment?

}