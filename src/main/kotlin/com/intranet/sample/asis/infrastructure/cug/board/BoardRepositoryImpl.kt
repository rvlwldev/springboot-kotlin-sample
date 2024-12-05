package com.intranet.sample.asis.infrastructure.cug.board

import com.intranet.sample.asis.domain.cug.board.Board
import com.intranet.sample.asis.domain.cug.board.BoardRepository
import org.jooq.DSLContext
import org.jooq.impl.DSL
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
class BoardRepositoryImpl(private val dsl: DSLContext) : BoardRepository {

    private val table = DSL.table("cug_board")
    private val uid = DSL.field("uid", Int::class.java)
    private val fid = DSL.field("fid", Int::class.java)
    private val name = DSL.field("name", String::class.java)
    private val email = DSL.field("email", String::class.java)
    private val homepage = DSL.field("homepage", String::class.java)
    private val subject = DSL.field("subject", String::class.java)
    private val comment = DSL.field("comment", String::class.java)
    private val passwd = DSL.field("passwd", String::class.java)
    private val signdate = DSL.field("signdate", Long::class.java)
    private val ref = DSL.field("ref", Int::class.java)
    private val thread = DSL.field("thread", String::class.java)
    private val myFile = DSL.field("my_file", String::class.java)

    override fun count() =
        dsl.selectCount()
            .from(table)
            .fetchOne(0, Int::class.java)!!

    override fun getEncryptedPassword(id: Int) =
        dsl.select(passwd)
            .from(table)
            .where(uid.eq(id))
            .fetchOneInto(String::class.java)

    override fun selectOne(id: Int) =
        dsl.select()
            .from(table)
            .where(uid.eq(id))
            .fetchOne { record -> if (record != null) Board.from(record) else null }

    override fun selectPage(pageable: Pageable): Page<Board> {
        val board = dsl.select()
            .from(table)
            .limit(DSL.inline(pageable.offset.toInt()), DSL.inline(pageable.pageSize))
            .fetch { record -> Board.from(record) }

        val total = dsl.selectCount()
            .from(table)
            .fetchOne(0, Int::class.java) ?: 0

        return PageImpl(board, pageable, total.toLong())
    }

    @Transactional
    override fun create(board: Board): Board? {
        dsl.insertInto(table)
            .set(fid, board.groupId)
            .set(name, board.userName)
            .set(email, board.userEmail)
            .set(homepage, board.userHomepage)
            .set(subject, board.title)
            .set(comment, board.content)
            .set(passwd, board.password)
            .set(signdate, board.createdAt)
            .set(ref, board.viewCount)
            .set(thread, board.depth)
            .set(myFile, board.fileDirectory)
            .execute()

        return dsl.fetchOne("SELECT LAST_INSERT_ID()")
            ?.get(0, Int::class.java)
            ?.let { selectOne(it) }
    }

    override fun update(board: Board) =
        dsl.update(table)
            .set(fid, board.groupId)
            .set(name, board.userName)
            .set(email, board.userEmail)
            .set(homepage, board.userHomepage)
            .set(subject, board.title)
            .set(comment, board.content)
            .set(passwd, board.password)
            .set(ref, board.viewCount)
            .where(uid.eq(board.id))
            .execute()

    override fun delete(id: Int) {
        dsl.deleteFrom(table)
            .where(uid.eq(id))
            .execute()
    }
}