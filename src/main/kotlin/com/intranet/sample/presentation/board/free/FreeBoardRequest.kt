package com.intranet.sample.presentation.board.free

import com.intranet.sample.presentation.core.validation.annotation.ValidAnonymousPassword
import jakarta.validation.constraints.NotBlank

class FreeBoardRequest {

    data class Post(
        @field:NotBlank
        val username: String,

        @field:ValidAnonymousPassword
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
        @field:ValidAnonymousPassword
        val password: String,
    )

}