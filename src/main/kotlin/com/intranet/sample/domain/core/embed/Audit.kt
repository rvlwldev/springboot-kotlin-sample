package com.intranet.sample.domain.core.embed

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import jakarta.persistence.PreUpdate
import org.hibernate.annotations.Comment
import java.time.LocalDateTime

@Embeddable
class Audit(

    @Column(name = "created_at", nullable = false, updatable = false)
    @Comment("생성일")
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "updated_at", nullable = true)
    @Comment("마지막 수정일")
    var updatedAt: LocalDateTime? = null,

    @Column(name = "deleted_at", nullable = true)
    @Comment("삭제일")
    var deletedAt: LocalDateTime? = null

) {

    /**
     * 삭제 시 삭제시간만 저장 (논리삭제)
     * */
    fun delete() {
        deletedAt = LocalDateTime.now()
    }

}


