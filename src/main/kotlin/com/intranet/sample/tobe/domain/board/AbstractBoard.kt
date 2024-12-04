package com.intranet.sample.tobe.domain.board

import com.intranet.sample.configuration.exception.BizException
import com.intranet.sample.tobe.domain.core.embed.Anonymous
import com.intranet.sample.tobe.domain.core.embed.Audit
import jakarta.persistence.Column
import jakarta.persistence.Embedded
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import jakarta.persistence.PreUpdate
import org.hibernate.annotations.Comment
import java.time.LocalDateTime

@MappedSuperclass
abstract class AbstractBoard(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("게시판 ID")
    val id: Long = 0L,

    @Embedded
    private var anonymous: Anonymous = Anonymous("익명", "null"),

    @Column(nullable = false)
    @Comment("제목")
    private var title: String = "",

    @Column(nullable = false, columnDefinition = "TEXT")
    @Comment("내용")
    private var content: String = "",

    @Embedded
    val audit: Audit = Audit()

) {

    fun getUsername() = this.anonymous.username
    fun getPassword() = this.anonymous.password
    fun getTitle() = this.title
    fun getContent() = this.content

    fun setUser(username: String, password: String) {
        if (username.isBlank()) throw BizException(BoardError.NOT_EMPTY_USERNAME)
        if (password.isBlank()) throw BizException(BoardError.NOT_EMPTY_PASSWORD)
        this.anonymous = Anonymous(username, password)
    }

    fun write(title: String, content: String) {
        if (title.isBlank()) throw BizException(BoardError.NOT_EMPTY_TITLE)
        if (content.isBlank()) throw BizException(BoardError.NOT_EMPTY_CONTENT)

        this.title = title
        this.content = content
    }

    /**
     * 수정 시 자동으로 마지막 수정일 UPDATE
     * */
    @PreUpdate
    private fun onUpdate() {
        audit.updatedAt = LocalDateTime.now()
    }

}