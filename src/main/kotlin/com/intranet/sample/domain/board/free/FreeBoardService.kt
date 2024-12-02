package com.intranet.sample.domain.board.free

import com.intranet.sample.domain.board.AbstractBoardService
import org.springframework.data.domain.Page
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class FreeBoardService(
    override val repo: FreeBoardRepository,
    override val passwordEncoder: PasswordEncoder
) : AbstractBoardService<FreeBoard, FreeBoardDTO.Info, FreeBoardDTO.PageInfo>() {

    override fun create(): FreeBoard = FreeBoard()
    override fun toInfo(board: FreeBoard): FreeBoardDTO.Info = FreeBoardDTO.Info(board)
    override fun toPage(page: Page<FreeBoard>): FreeBoardDTO.PageInfo = FreeBoardDTO.PageInfo(page)

}