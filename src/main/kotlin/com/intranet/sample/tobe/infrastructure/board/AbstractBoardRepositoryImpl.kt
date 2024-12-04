package com.intranet.sample.tobe.infrastructure.board

import com.intranet.sample.tobe.domain.board.AbstractBoard
import com.intranet.sample.tobe.domain.board.AbstractBoardRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

abstract class AbstractBoardRepositoryImpl<T : AbstractBoard> :
    AbstractBoardRepository<T> {

    protected abstract val jpa: DefaultBoardJpaRepository<T>

    override fun save(board: T): T =
        jpa.save(board)

    override fun findAllNotDeleted(pageable: Pageable): Page<T> =
        jpa.findAllByAuditDeletedAtIsNull(pageable)

    override fun findById(id: Long): T? =
        jpa.findById(id).orElse(null)

    override fun delete(board: T) =
        jpa.delete(board)

}