package com.intranet.sample.domain.board

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface BoardRepository {

    fun save(board: Board): Board
    fun findAllNotDeleted(pageable: Pageable): Page<Board>
    fun findById(id: Long): Board?
    fun delete(board: Board)

}