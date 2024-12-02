package com.intranet.sample.presentation.board

import com.intranet.sample.domain.board.BoardService
import jakarta.validation.Valid
import org.springframework.data.domain.PageRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController("/board")
class BoardController(private val service: BoardService) {

    @GetMapping("/{id}")
    fun getOne(@PathVariable("id") id: Long) =
        service.getDetail(id)
            .let { BoardResponse.Detail(it) }
            .let { ResponseEntity.ok(it) }

    @GetMapping
    fun getPage(
        @RequestParam("page") page: Int = 0,
        @RequestParam("size") size: Int = 10,
    ): ResponseEntity<BoardResponse.Page> =
        service.getPage(PageRequest.of(page, size))
            .let { BoardResponse.Page(it) }
            .let { ResponseEntity.ok(it) }

    @PostMapping
    fun post(@RequestBody @Valid request: BoardRequest.Post): ResponseEntity<Unit> =
        service.create(request.username, request.password, request.title, request.content)
            .let { ResponseEntity.created(URI.create("/board/${it.id}")).build() }

    @PutMapping("/{id}")
    fun update(
        @PathVariable("id") id: Long,
        @RequestBody @Valid request: BoardRequest.Update
    ): ResponseEntity<BoardResponse.Detail> =
        service.update(id, request.password, request.title, request.content)
            .let { BoardResponse.Detail(it) }
            .let { ResponseEntity.ok(it) }


    @DeleteMapping("/{id}")
    fun delete(
        @PathVariable("id") id: Long,
        @RequestBody @Valid request: BoardRequest.Delete
    ): ResponseEntity<Unit> =
        service.delete(id, request.password)
            .let { ResponseEntity.noContent().build() }
}