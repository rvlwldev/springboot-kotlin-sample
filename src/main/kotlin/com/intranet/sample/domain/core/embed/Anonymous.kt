package com.intranet.sample.domain.core.embed

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import org.hibernate.annotations.Comment

@Embeddable
class Anonymous(

    @Column(nullable = false)
    @Comment("작성자 이름")
    val username: String = "",

    @Column(nullable = false)
    @Comment("비밀번호(암호화)")
    val password: String = ""

)