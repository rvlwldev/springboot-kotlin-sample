package com.intranet.sample.presentation.board.free

import jakarta.validation.constraints.NotBlank
import org.hibernate.validator.constraints.Length

class FreeBoardRequest {

    data class Post(
        @field:NotBlank
        val username: String,

        @field:NotBlank
        @field:Length(min = 4)
        val password: String,

        @field:NotBlank
        val title: String,

        @field:NotBlank
        val content: String
    )

    data class Update(
        @field:NotBlank
        val password: String,

        @field:NotBlank
        val title: String,

        @field:NotBlank
        val content: String
    )

    data class Delete(
        @field:NotBlank
        val password: String,
    )

}