package com.intranet.sample.tobe.domain.board.announce

import com.intranet.sample.tobe.domain.board.BoardDTO
import com.intranet.sample.tobe.presentation.board.announce.AnnounceBoardRequest
import org.springframework.data.domain.Page
import java.time.LocalDateTime

class AnnounceBoardDTO {
    class Info(
        id: Long,
        title: String,
        content: String,
        username: String,
        createdAt: LocalDateTime,
        updatedAt: LocalDateTime?,

        val type: String,
    ) : BoardDTO.OpenInfo<AnnounceBoard>(id, title, content, username, createdAt, updatedAt) {
        constructor(board: AnnounceBoard) : this(
            id = board.id,
            title = board.getTitle(),
            content = board.getContent(),
            username = board.getUsername(),
            createdAt = board.audit.createdAt,
            updatedAt = board.audit.updatedAt,

            type = board.type.name
        )
    }

    class PageInfo(page: Page<AnnounceBoard>) :
        BoardDTO.OpenPageInfo<AnnounceBoard, Info>(page = page, mapper = { Info(it) })

    class Command {
        class Post(
            val username: String,
            val password: String,
            val title: String,
            val content: String,
            val type: String
        ) {
            constructor(request: AnnounceBoardRequest.Post) : this(
                username = request.username,
                password = request.password,
                title = request.title,
                content = request.content,
                type = request.type
            )
        }
    }

}