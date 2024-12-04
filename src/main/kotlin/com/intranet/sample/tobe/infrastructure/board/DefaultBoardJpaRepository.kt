package com.intranet.sample.tobe.infrastructure.board

import com.intranet.sample.tobe.domain.board.AbstractBoard
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.NoRepositoryBean

@NoRepositoryBean
interface DefaultBoardJpaRepository<T : AbstractBoard> : JpaRepository<T, Long> {
    fun findAllByAuditDeletedAtIsNull(pageable: Pageable): Page<T>
}