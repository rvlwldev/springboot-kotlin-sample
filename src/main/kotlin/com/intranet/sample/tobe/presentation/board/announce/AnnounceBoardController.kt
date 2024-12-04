package com.intranet.sample.tobe.presentation.board.announce

import com.intranet.sample.tobe.domain.board.announce.AnnounceBoardService
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
@RequestMapping("/announce")
class AnnounceBoardController(private val service: AnnounceBoardService) {

    @GetMapping("/{id}")
    fun getOne(@PathVariable("id") id: Long): ResponseEntity<AnnounceBoardResponse.Detail> =
        service.getDetail(id)
            .let { AnnounceBoardResponse.Detail(it) }
            .let { ResponseEntity.ok(it) }

    @GetMapping
    fun getPage(
        @RequestParam("page") page: Int = 0,
        @RequestParam("size") size: Int = 10,
    ): ResponseEntity<AnnounceBoardResponse.Page> =
        service.getPage(PageRequest.of(page, size))
            .let { AnnounceBoardResponse.Page(it) }
            .let { ResponseEntity.ok(it) }

    @PostMapping
    fun post(@RequestBody @Valid request: AnnounceBoardRequest.Post): ResponseEntity<Unit> =
        service.save(request.username, request.password, request.title, request.content)
            .let { ResponseEntity.created(URI.create("/free/${it.id}")).build() }

    @PutMapping("/{id}")
    fun update(
        @PathVariable("id") id: Long,
        @RequestBody @Valid request: AnnounceBoardRequest.Update
    ): ResponseEntity<AnnounceBoardResponse.Detail> =
        service.update(id, request.password, request.title, request.content)
            .let { URI.create("/announce/${it.id}") }
            .let { uri -> ResponseEntity.noContent().location(uri).build() }

    @DeleteMapping("/{id}")
    fun delete(
        @PathVariable("id") id: Long,
        @RequestBody @Valid request: AnnounceBoardRequest.Delete
    ): ResponseEntity<Unit> =
        service.delete(id, request.password)
            .let { ResponseEntity.noContent().build() }

}