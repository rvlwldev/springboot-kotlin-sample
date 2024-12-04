package com.intranet.sample.tobe.presentation.board.announce

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import org.hibernate.validator.constraints.Length

class AnnounceBoardRequest {

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

}