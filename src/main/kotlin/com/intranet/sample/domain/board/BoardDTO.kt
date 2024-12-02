package com.intranet.sample.domain.board

import org.springframework.data.domain.Page
import java.time.LocalDateTime

class BoardDTO {

    data class Info(
        val id: Long,
        val title: String,
        val content: String,
        val username: String,
        val createdAt: LocalDateTime,
        val updatedAt: LocalDateTime?,
    ) {
        constructor(board: Board) : this(
            id = board.id,
            title = board.getTitle(),
            content = board.getContent(),
            username = board.getUsername(),
            createdAt = board.audit.createdAt,
            updatedAt = board.audit.updatedAt
        )
    }

    data class SimpleInfo(
        val id: Long,
        val title: String,
        val username: String,
        val createdAt: LocalDateTime,
    ) {
        constructor(board: Board) : this(
            id = board.id,
            title = board.getTitle(),
            username = board.getUsername(),
            createdAt = board.audit.createdAt
        )
    }

    data class PageableSimpleInfo(
        val list: List<SimpleInfo>,
        val total: Long,
        val current: Long,
        val size: Long,
    ) {
        constructor(board: Page<Board>) : this(
            list = board.content.map { SimpleInfo(it) },
            total = board.totalPages.toLong(),
            current = board.number.toLong(),
            size = board.size.toLong()
        )
    }

}