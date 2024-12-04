package com.intranet.sample.tobe.presentation.board.free

import com.intranet.sample.tobe.domain.board.free.FreeBoardDTO
import java.time.LocalDateTime

class FreeBoardResponse {

    data class Detail(
        val id: Long,
        val username: String,
        val title: String,
        val content: String,
        val createdAt: LocalDateTime,
        val updatedAt: LocalDateTime?
    ) {
        constructor(info: FreeBoardDTO.Info) : this(
            id = info.id,
            username = info.username,
            title = info.title,
            content = info.content,
            createdAt = info.createdAt,
            updatedAt = info.updatedAt,
        )
    }

    data class Simple(
        val id: Long,
        val title: String,
        val username: String,
        val createdAt: LocalDateTime,
    ) {
        constructor(info: FreeBoardDTO.Info) : this(
            id = info.id,
            title = info.title,
            username = info.username,
            createdAt = info.createdAt,
        )
    }

    data class Page(
        val list: List<Simple>,
        val total: Int,
        val current: Int,
        val size: Int
    ) {
        constructor(info: FreeBoardDTO.PageInfo) : this(
            list = info.list.map { Simple(it) },
            total = info.total,
            current = info.current + 1,
            size = info.size
        )
    }
}