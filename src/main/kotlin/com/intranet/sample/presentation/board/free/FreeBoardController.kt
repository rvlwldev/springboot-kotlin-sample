package com.intranet.sample.presentation.board.free

import com.intranet.sample.domain.board.free.FreeBoardService
import jakarta.validation.Valid
import org.springframework.data.domain.PageRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
@RequestMapping("/free")
class FreeBoardController(private val service: FreeBoardService) {

    @GetMapping("/{id}")
    fun getOne(@PathVariable("id") id: Long): ResponseEntity<FreeBoardResponse.Detail> =
        service.getDetail(id)
            .let { FreeBoardResponse.Detail(it) }
            .let { ResponseEntity.ok(it) }

    @GetMapping
    fun getPage(
        @RequestParam("page") page: Int = 0,
        @RequestParam("size") size: Int = 10,
    ): ResponseEntity<FreeBoardResponse.Page> =
        service.getPage(PageRequest.of(page, size))
            .let { FreeBoardResponse.Page(it) }
            .let { ResponseEntity.ok(it) }

    @PostMapping
    fun post(@RequestBody @Valid request: FreeBoardRequest.Post): ResponseEntity<Unit> =
        service.save(request.username, request.password, request.title, request.content)
            .let { ResponseEntity.created(URI.create("/free/${it.id}")).build() }

    @PutMapping("/{id}")
    fun update(
        @PathVariable("id") id: Long,
        @RequestBody @Valid request: FreeBoardRequest.Update
    ): ResponseEntity<Unit> =
        service.update(id, request.password, request.title, request.content)
            .let { URI.create("/free/${it.id}") }
            .let { uri -> ResponseEntity.noContent().location(uri).build() }

    @DeleteMapping("/{id}")
    fun delete(
        @PathVariable("id") id: Long,
        @RequestBody @Valid request: FreeBoardRequest.Delete
    ): ResponseEntity<Unit> =
        service.delete(id, request.password)
            .let { ResponseEntity.noContent().build() }

}