package com.intranet.sample.presentation.board.discuss

import com.intranet.sample.domain.board.discuss.DiscussBoardService
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
@RequestMapping("/discuss")
class DiscussBoardController(private val service: DiscussBoardService) {

    @GetMapping("/{id}")
    fun getOne(@PathVariable("id") id: Long): ResponseEntity<DiscussBoardResponse.Detail> =
        service.getDetail(id)
            .let { DiscussBoardResponse.Detail(it) }
            .let { ResponseEntity.ok(it) }

    @GetMapping
    fun getPage(
        @RequestParam("page") page: Int = 0,
        @RequestParam("size") size: Int = 10,
    ): ResponseEntity<DiscussBoardResponse.Page> =
        service.getPage(PageRequest.of(page, size))
            .let { DiscussBoardResponse.Page(it) }
            .let { ResponseEntity.ok(it) }

    @PostMapping
    fun post(@RequestBody @Valid request: DiscussBoardRequest.Post): ResponseEntity<Unit> =
        service.save(request.username, request.password, request.title, request.content)
            .let { ResponseEntity.created(URI.create("/free/${it.id}")).build() }

    @PutMapping("/{id}")
    fun update(
        @PathVariable("id") id: Long,
        @RequestBody @Valid request: DiscussBoardRequest.Update
    ): ResponseEntity<DiscussBoardResponse.Detail> =
        service.update(id, request.password, request.title, request.content)
            .let { URI.create("/discuss/${it.id}") }
            .let { uri -> ResponseEntity.noContent().location(uri).build() }

    @DeleteMapping("/{id}")
    fun delete(
        @PathVariable("id") id: Long,
        @RequestBody @Valid request: DiscussBoardRequest.Delete
    ): ResponseEntity<Unit> =
        service.delete(id, request.password)
            .let { ResponseEntity.noContent().build() }

}