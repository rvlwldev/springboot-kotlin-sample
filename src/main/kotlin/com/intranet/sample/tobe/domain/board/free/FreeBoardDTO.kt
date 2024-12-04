package com.intranet.sample.tobe.domain.board.free

import com.intranet.sample.tobe.domain.board.BoardDTO
import org.springframework.data.domain.Page

class FreeBoardDTO {
    class Info(board: FreeBoard) : BoardDTO.OpenInfo<FreeBoard>(
        id = board.id,
        title = board.getTitle(),
        content = board.getContent(),
        username = board.getUsername(),
        createdAt = board.audit.createdAt,
        updatedAt = board.audit.updatedAt,
    )

    class PageInfo(page: Page<FreeBoard>) : BoardDTO.OpenPageInfo<FreeBoard, Info>(
        page = page,
        mapper = { Info(it) }
    )
}