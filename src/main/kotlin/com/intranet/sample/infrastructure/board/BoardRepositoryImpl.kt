package com.intranet.sample.infrastructure.board

import com.intranet.sample.domain.board.Board
import com.intranet.sample.domain.board.BoardRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

interface BoardJpaRepository : JpaRepository<Board, Long> {
    fun findAllByAuditDeletedAtIsNull(pageable: Pageable): Page<Board>
}

@Repository
class BoardRepositoryImpl(private val jpa: BoardJpaRepository) : BoardRepository {

    override fun save(board: Board): Board =
        jpa.save(board)

    override fun findAllNotDeleted(pageable: Pageable): Page<Board> =
        jpa.findAllByAuditDeletedAtIsNull(pageable)

    override fun findById(id: Long): Board? =
        jpa.findById(id).orElse(null)

    override fun delete(board: Board) {
        board.audit.delete()
        jpa.save(board)
    }

}