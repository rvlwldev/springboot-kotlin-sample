package com.intranet.sample.tobe.infrastructure.board.discuss

import com.intranet.sample.tobe.domain.board.discuss.DiscussComment
import org.springframework.data.jpa.repository.JpaRepository

interface DiscussCommentJpaRepository : JpaRepository<DiscussComment, Long> {

    fun findAllByBoardId(boardId: Long): List<DiscussComment>
    fun findByBoardIdAndId(boardId: Long, commentId: Long): DiscussComment?

}