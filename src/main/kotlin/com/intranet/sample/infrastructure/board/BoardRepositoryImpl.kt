package com.intranet.sample.infrastructure.board

import com.intranet.sample.domain.board.AbstractBoard
import com.intranet.sample.domain.board.AbstractBoardRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

interface BoardJpaRepository : JpaRepository<AbstractBoard, Long> {
    fun findAllByAuditDeletedAtIsNull(pageable: Pageable): Page<AbstractBoard>
}

@Repository
class BoardRepositoryImpl(private val jpa: BoardJpaRepository) : AbstractBoardRepository {

    override fun save(board: AbstractBoard): AbstractBoard =
        jpa.save(board)

    override fun findAllNotDeleted(pageable: Pageable): Page<AbstractBoard> =
        jpa.findAllByAuditDeletedAtIsNull(pageable)

    override fun findById(id: Long): AbstractBoard? =
        jpa.findById(id).orElse(null)

    override fun delete(board: AbstractBoard) {
        board.audit.delete()
        jpa.save(board)
    }

}