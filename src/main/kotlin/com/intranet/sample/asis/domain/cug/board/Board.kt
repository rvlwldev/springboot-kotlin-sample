package com.intranet.sample.asis.domain.cug.board

import com.intranet.sample.configuration.exception.BizException
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.jooq.Record
import org.springframework.http.HttpStatus
import java.time.LocalDateTime
import java.time.ZoneId

@Entity
@Table(name = "cug_board")
class Board(

    @Id
    @Column(name = "uid", columnDefinition = "mediumint unsigned", nullable = false)
    val id: Int = 0,

    @Column(name = "fid", nullable = false, columnDefinition = "mediumint unsigned")
    val groupId: Int = 0,

    @Column(name = "name", nullable = false, length = 12)
    var userName: String,

    @Column(name = "passwd", nullable = false, length = 30)
    var password: String,

    @Column(name = "email", length = 40)
    var userEmail: String? = null,

    @Column(name = "homepage", length = 60)
    var userHomepage: String? = null,

    @Column(name = "subject", nullable = false, length = 60)
    var title: String,

    @Column(name = "comment", nullable = false, columnDefinition = "text")
    var content: String,

    @Column(name = "signdate", nullable = false, columnDefinition = "int unsigned")
    val createdAt: Long = LocalDateTime.now()
        .atZone(ZoneId.of("Asia/Seoul"))
        .toEpochSecond(),

    @Column(name = "ref", nullable = false, columnDefinition = "smallint unsigned")
    var viewCount: Int = 0,

    @Column(name = "thread", nullable = false, length = 255)
    val depth: String = "",

    @Column(name = "my_file", length = 50)
    val fileDirectory: String = ""

) {
    fun validatePassword(password: String) {
        if (this.password != password)
            throw BizException(HttpStatus.FORBIDDEN, "비번 틀림")
    }

    fun increaseView(): Board {
        this.viewCount++
        return this
    }

    fun update(
        userName: String,
        password: String,
        userEmail: String?,
        userHomepage: String?,
        title: String,
        content: String
    ) {
        this.userName = userName
        this.password = password
        this.userEmail = userEmail
        this.userHomepage = userHomepage
        this.title = title
        this.content = content
    }

    companion object {
        fun from(record: Record): Board {
            return Board(
                id = record.get("uid", Int::class.java)!!,
                groupId = record.get("fid", Int::class.java)!!,
                userName = record.get("name", String::class.java)!!,
                userEmail = record.get("email", String::class.java),
                userHomepage = record.get("homepage", String::class.java),
                title = record.get("subject", String::class.java)!!,
                content = record.get("comment", String::class.java)!!,
                password = record.get("passwd", String::class.java)!!,
                createdAt = record.get("signdate", Long::class.java)!!,
                viewCount = record.get("ref", Int::class.java)!!,
                depth = record.get("thread", String::class.java)!!,
                fileDirectory = record.get("my_file", String::class.java)!!
            )
        }
    }
}