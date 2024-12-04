package com.intranet.sample.tobe.domain.board.announce

import com.intranet.sample.tobe.domain.core.embed.Anonymous
import com.intranet.sample.tobe.domain.core.embed.Audit
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

) : com.intranet.sample.tobe.domain.board.AbstractBoard(
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