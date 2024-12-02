package com.intranet.sample.domain.board.announce

import com.intranet.sample.domain.board.AbstractBoard
import com.intranet.sample.domain.core.embed.Anonymous
import com.intranet.sample.domain.core.embed.Audit
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Table

enum class AnnounceBoardType {
    ALERT, EMERGENCY
}

@Entity
@Table(name = "board_announce")
class AnnounceBoard(
    username: String = "",
    password: String = "",
    title: String = "",
    content: String = "",

    @Enumerated(EnumType.STRING)
    val type: AnnounceBoardType = AnnounceBoardType.ALERT

) : AbstractBoard(
    anonymous = Anonymous(username, password),
    title = title,
    content = content,
    audit = Audit()
) {
    constructor(command: AnnounceBoardDTO.Command.Post) : this(
        username = command.username,
        password = command.password,
        type = AnnounceBoardType.valueOf(command.type)
    )
}