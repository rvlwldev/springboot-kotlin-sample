package com.intranet.sample.asis.domain.cug.board

import jakarta.validation.constraints.NotNull

class BoardDTO {
    data class Command(
        @NotNull(message = "필수값임") val userName: String,
        @NotNull(message = "필수값임") val password: String,
        val userEmail: String?,
        val userHomepage: String?,
        @NotNull(message = "필수값임") val title: String,
        @NotNull(message = "필수값임") val content: String,
        val parentId: Int?
    )
}