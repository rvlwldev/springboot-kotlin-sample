package com.intranet.sample.configuration.exception

import org.springframework.http.HttpStatus

class ExceptionResponse(
    private val error: Boolean = true,
    val status: Int = 500,
    val message: String = "서버 에러가 발생했습니다."
) {
    constructor(httpStatus: HttpStatus, message: String) : this(
        status = httpStatus.value(),
        message = message
    )
}