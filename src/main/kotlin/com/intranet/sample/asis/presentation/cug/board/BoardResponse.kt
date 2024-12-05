package com.intranet.sample.asis.presentation.cug.board

import com.fasterxml.jackson.databind.ObjectMapper
import com.intranet.sample.asis.domain.cug.board.Board
import org.springframework.data.domain.Page

class BoardResponse(private val mapper: ObjectMapper) {
    data class Pagination(
        val list: List<Board>,
        val total: Int,
        val current: Int,
        val size: Int
    ) {
        constructor(page: Page<Board>) : this(
            list = page.content,
            total = page.totalPages,
            current = page.number + 1,
            size = page.size,
        )
    }
}