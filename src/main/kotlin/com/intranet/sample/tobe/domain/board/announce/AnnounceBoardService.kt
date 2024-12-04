package com.intranet.sample.tobe.domain.board.announce

import com.intranet.sample.tobe.domain.board.AbstractBoardService
import org.springframework.data.domain.Page
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AnnounceBoardService(
    override val repo: AnnounceBoardRepository,
    override val passwordEncoder: PasswordEncoder
) : AbstractBoardService<AnnounceBoard, AnnounceBoardDTO.Info, AnnounceBoardDTO.PageInfo>() {

    override fun create() = AnnounceBoard()
    override fun toInfo(board: AnnounceBoard) = AnnounceBoardDTO.Info(board)
    override fun toPage(page: Page<AnnounceBoard>) = AnnounceBoardDTO.PageInfo(page)

    /**
     * 방법1. 그냥 매개변수 나열 ...
     * */
    fun save(username: String, password: String, title: String, content: String, type: String): AnnounceBoardDTO.Info {
        val board = AnnounceBoard(username, password, title, content, type = AnnounceBoardType.valueOf(type))
        board.write(title, content)

        return AnnounceBoardDTO.Info(repo.save(board))
    }

    /**
     * 방법2. CQRS 의 Command 패턴처럼 입력 DTO 받아서 보조 생성자 추가
     * */
    fun save(command: AnnounceBoardDTO.Command.Post): AnnounceBoardDTO.Info =
        AnnounceBoard(command).let { repo.save(it) }
            .let { AnnounceBoardDTO.Info(it) }

}