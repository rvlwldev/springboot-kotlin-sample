package com.intranet.sample.configuration.exception

import org.springframework.http.HttpStatus

class BizException(
    val status: HttpStatus = HttpStatus.INTERNAL_SERVER_ERROR,
    override val message: String = "에러발생"
) : RuntimeException(message) {
    constructor(e: Pair<HttpStatus, String>) : this(
        status = e.first,
        message = e.second
    )
}