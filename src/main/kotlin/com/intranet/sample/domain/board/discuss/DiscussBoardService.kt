package com.intranet.sample.domain.board.discuss

import com.intranet.sample.domain.board.AbstractBoardService
import org.springframework.data.domain.Page
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class DiscussBoardService(
    override val repo: DiscussBoardRepository,
    override val passwordEncoder: PasswordEncoder
) : AbstractBoardService<DiscussBoard, DiscussBoardDTO.Info, DiscussBoardDTO.PageInfo>() {

    override fun create() = DiscussBoard()
    override fun toInfo(board: DiscussBoard) = DiscussBoardDTO.Info(board)
    override fun toPage(page: Page<DiscussBoard>) = DiscussBoardDTO.PageInfo(page)

}