package com.intranet.sample.asis.domain.cug.board

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface BoardRepository {

    fun count(): Int
    fun selectOne(id: Int): Board?
    fun selectPage(pageable: Pageable): Page<Board>

    fun create(board: Board): Board?
    fun update(board: Board): Int
    fun delete(id: Int)

    fun getEncryptedPassword(id: Int): String?

}