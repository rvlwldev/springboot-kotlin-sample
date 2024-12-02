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

    data class PageableInfo(
        val list: List<Info>,
        val total: Int,
        val current: Int,
        val size: Int,
    ) {
        constructor(board: Page<Board>) : this(
            list = board.content.map { Info(it) },
            total = board.totalPages,
            current = board.number,
            size = board.size
        )
    }

}