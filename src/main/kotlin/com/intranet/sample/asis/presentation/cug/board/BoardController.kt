package com.intranet.sample.asis.presentation.cug.board

import com.intranet.sample.asis.domain.cug.board.BoardDTO
import com.intranet.sample.asis.domain.cug.board.BoardService
import jakarta.validation.Valid
import org.springframework.data.domain.PageRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
@RequestMapping("/cug/board")
class BoardController(private val service: BoardService) {

    @GetMapping
    fun getAll(
        @RequestParam("page") page: Int = 0,
        @RequestParam("size") size: Int = 10,
    ) = service.getPage(PageRequest.of(page, size))
        .let { BoardResponse.Pagination(it) }
        .let { ResponseEntity.ok(it) }

    @GetMapping("/{id}")
    fun getOne(@PathVariable("id") id: Int) = service.getDetail(id)
        .let { ResponseEntity.ok(it) }

    @PostMapping
    fun post(@RequestBody @Valid body: BoardDTO.Command): ResponseEntity<Unit> =
        service.create(body)
            .let { URI.create("/cug/board/${it.id}") }
            .let { uri -> ResponseEntity.created((URI.create("/cug/board/$uri"))).build() }

    @PutMapping("/{id}")
    fun update(
        @PathVariable("id") id: Int,
        @RequestBody @Valid body: BoardDTO.Command
    ): ResponseEntity<Unit> = service.update(id, body)
        .let { URI.create("/cug/board/${it.id}") }
        .let { uri -> ResponseEntity.noContent().location(uri).build() }

    @DeleteMapping("/{id}")
    fun delete(
        @PathVariable("id") id: Int,
        @RequestHeader("password") password: String
    ): ResponseEntity<Unit> = service.delete(id, password)
        .let { ResponseEntity.noContent().build() }

}