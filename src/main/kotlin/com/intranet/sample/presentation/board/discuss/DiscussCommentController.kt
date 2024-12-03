package com.intranet.sample.presentation.board.discuss

import com.intranet.sample.domain.board.discuss.DiscussBoardService
import jakarta.validation.Valid
import jakarta.validation.constraints.Positive
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
@RequestMapping("/discuss/{id}/comments")
class DiscussCommentController(private val service: DiscussBoardService) {

    @GetMapping
    fun getAll(@PathVariable("id") boardId: Long): ResponseEntity<List<DiscussBoardResponse.Comment>> =
        service.getAllComment(boardId)
            .let { list -> list.map { DiscussBoardResponse.Comment(it) } }
            .let { ResponseEntity.ok(it) }

    @GetMapping("/{commentId}")
    fun get(
        @PathVariable("id") boardId: Long,
        @PathVariable("commentId") @Positive id: Long
    ) = service.getComment(boardId, id)
        .let { DiscussBoardResponse.Comment(it) }
        .let { ResponseEntity.ok(it) }

    @PostMapping
    fun post(
        @PathVariable("id") @Positive boardId: Long,
        @RequestBody @Valid body: DiscussBoardRequest.Comment.Post
    ): ResponseEntity<Unit> = service.saveComment(boardId, body.username, body.password, body.content)
        .let { ResponseEntity.created(URI("/discuss/$boardId?comment=${it.id}")).build() }

    @PutMapping("/{commentId}")
    fun update(
        @PathVariable("id") boardId: Long,
        @RequestBody @Valid body: DiscussBoardRequest.Comment.Update
    ): ResponseEntity<Unit> = service.updateComment(boardId, body.id, body.password, body.content)
        .let { ResponseEntity.noContent().location(URI("/discuss/$boardId?comment=${it.id}")).build() }

    @DeleteMapping("/{commentId}")
    fun delete(
        @PathVariable("id") boardId: Long,
        @RequestBody @Valid body: DiscussBoardRequest.Comment.Delete
    ): ResponseEntity<Unit> = service.deleteComment(boardId, body.id, body.password)
        .let { ResponseEntity.noContent().build() }

}