package com.intranet.sample.domain.board.discuss

import com.intranet.sample.domain.core.embed.Anonymous
import com.intranet.sample.domain.core.embed.Audit
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
import java.time.LocalDateTime

@Entity
@Table(name = "discuss_comments")
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
    protected var content: String = "",

    @Embedded
    val anonymous: Anonymous = Anonymous("익명", "null"),

    @Embedded
    val audit: Audit = Audit()
) {

    fun write(content: String) {
        if (content.isBlank()) throw IllegalArgumentException()
        this.content = content
    }

    @PreUpdate
    private fun onUpdate() {
        audit.updatedAt = LocalDateTime.now()
    }

}