package com.intranet.sample.presentation.board

import com.intranet.sample.domain.board.BoardDTO
import java.time.LocalDateTime

class BoardResponse {

    data class Detail(
        val id: Long,
        val username: String,
        val title: String,
        val content: String,
        val createdAt: LocalDateTime,
        val updatedAt: LocalDateTime?
    ) {
        constructor(board: BoardDTO.Info) : this(
            id = board.id,
            username = board.username,
            title = board.title,
            content = board.title,
            createdAt = board.createdAt,
            updatedAt = board.updatedAt,
        )
    }

    data class Simple(
        val id: Long,
        val title: String,
        val createdAt: LocalDateTime
    ) {
        constructor(board: BoardDTO.Info) : this(
            id = board.id,
            title = board.title,
            createdAt = board.createdAt,
        )
    }

    data class Page(
        val list: List<Simple>,
        val total: Int,
        val current: Int,
        val size: Int
    ) {
        constructor(page: BoardDTO.PageableInfo) : this(
            list = page.list.map { Simple(it) },
            total = page.total,
            current = page.current + 1,
            size = page.size
        )
    }

}