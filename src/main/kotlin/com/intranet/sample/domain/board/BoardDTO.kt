package com.intranet.sample.domain.board

import org.springframework.data.domain.Page
import java.time.LocalDateTime

class BoardDTO {

    open class OpenInfo<T : AbstractBoard>(
        val id: Long,
        val title: String,
        val content: String,
        val username: String,
        val createdAt: LocalDateTime,
        val updatedAt: LocalDateTime?,
    ) {
        constructor(board: T) : this(
            id = board.id,
            title = board.getTitle(),
            content = board.getContent(),
            username = board.getUsername(),
            createdAt = board.audit.createdAt,
            updatedAt = board.audit.updatedAt
        )
    }

    open class OpenPageInfo<T : AbstractBoard, I : OpenInfo<T>>(
        val list: List<I>,
        val total: Int,
        val current: Int,
        val size: Int
    ) {
        constructor(page: Page<T>, mapper: (T) -> I) : this(
            list = page.content.map(mapper),
            total = page.totalPages,
            current = page.number,
            size = page.size
        )
    }
}