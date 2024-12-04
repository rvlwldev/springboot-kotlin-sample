package com.intranet.sample.tobe.presentation.board.discuss

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Positive
import org.hibernate.validator.constraints.Length

class DiscussBoardRequest {

    data class Post(
        @field:NotBlank
        val username: String,

        @field:NotBlank
        @field:Length(min = 4)
        val password: String,

        @field:NotBlank
        val title: String,

        @field:NotBlank
        val content: String,

        @field:Pattern(regexp = "ALERT|EMERGENCY")
        val type: String
    )

    data class Update(
        @field:NotBlank
        val password: String,

        @field:NotBlank
        val title: String,

        @field:NotBlank
        val content: String,

        @field:Pattern(regexp = "ALERT|EMERGENCY")
        val type: String
    )

    data class Delete(
        @field:NotBlank
        val password: String,
    )

    class Comment {
        data class Post(
            @field:NotBlank
            val username: String,

            @field:NotBlank
            @field:Length(min = 4)
            val password: String,

            @field:NotBlank
            val content: String
        )

        data class Update(
            @field:Positive
            val id: Long,

            @field:NotBlank
            @field:Length(min = 4)
            val password: String,

            @field:NotBlank
            val content: String
        )

        data class Delete(
            @field:Positive
            val id: Long,

            @field:NotBlank
            @field:Length(min = 4)
            val password: String,
        )

    }

}