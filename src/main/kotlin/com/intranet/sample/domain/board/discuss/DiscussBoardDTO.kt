package com.intranet.sample.domain.board.discuss

import com.intranet.sample.domain.board.BoardDTO
import org.springframework.data.domain.Page
import java.time.LocalDateTime

class DiscussBoardDTO {
    class Info(
        id: Long,
        title: String,
        content: String,
        username: String,
        createdAt: LocalDateTime,
        updatedAt: LocalDateTime?
    ) : BoardDTO.OpenInfo<DiscussBoard>(id, title, content, username, createdAt, updatedAt) {
        constructor(board: DiscussBoard) : this(
            id = board.id,
            title = board.getTitle(),
            content = board.getContent(),
            username = board.getUsername(),
            createdAt = board.audit.createdAt,
            updatedAt = board.audit.updatedAt
        )
    }

    class PageInfo(page: Page<DiscussBoard>) :
        BoardDTO.OpenPageInfo<DiscussBoard, Info>(page = page, mapper = { Info(it) })

}