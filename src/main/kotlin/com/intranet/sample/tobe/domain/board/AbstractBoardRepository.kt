package com.intranet.sample.tobe.domain.board

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface AbstractBoardRepository<T : AbstractBoard> {

    fun save(board: T): T
    fun findAllNotDeleted(pageable: Pageable): Page<T>
    fun findById(id: Long): T?
    fun delete(board: T)

}