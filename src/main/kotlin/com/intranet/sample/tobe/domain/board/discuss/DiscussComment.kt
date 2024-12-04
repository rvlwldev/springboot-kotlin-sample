package com.intranet.sample.tobe.domain.board.discuss

import com.intranet.sample.tobe.domain.core.embed.Anonymous
import com.intranet.sample.tobe.domain.core.embed.Audit
import jakarta.persistence.Column
import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.PreUpdate
import jakarta.persistence.Table
import org.hibernate.annotations.Comment
import org.springframework.security.crypto.password.PasswordEncoder
import java.time.LocalDateTime

@Entity
@Table(name = "board_discuss_comments")
class DiscussComment(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment(value = "댓글 ID")
    val id: Long = 0L,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id", nullable = false)
    @Comment(value = "게시판 ID")
    val board: DiscussBoard,

    @Column(nullable = false)
    @Comment(value = "댓글 내용")
    var content: String = "",

    @Embedded
    val anonymous: Anonymous = Anonymous("익명", "null"),

    @Embedded
    val audit: Audit = Audit()
) {

//    fun getContent() = this.content

    constructor(board: DiscussBoard, username: String, password: String, content: String) : this(
        board = board.also { if (it.id == 0L) throw IllegalArgumentException() },
        anonymous = Anonymous(
            username.ifBlank { throw IllegalArgumentException() },
            password.ifBlank { throw IllegalArgumentException() }),
        content = content.ifBlank { throw IllegalArgumentException() }
    )

    // 여기서 더 OOP?
    fun validatePassword(encoder: PasswordEncoder, rawPassword: String) =
        if (!encoder.matches(rawPassword, this.anonymous.password)) throw IllegalArgumentException()
        else true

    fun write(content: String) {
        if (content.isBlank()) throw IllegalArgumentException()
        this.content = content
    }

    @PreUpdate
    private fun onUpdate() {
        audit.updatedAt = LocalDateTime.now()
    }

}