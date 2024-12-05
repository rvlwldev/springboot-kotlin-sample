package com.intranet.sample.configuration.exception

import jakarta.validation.ConstraintViolationException
import jakarta.validation.ValidationException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalControllerAdvice {

    private val logger: Logger = LoggerFactory.getLogger(GlobalControllerAdvice::class.java)

    // 주로 @RequestParam, @PathVariable
    @ExceptionHandler(ConstraintViolationException::class)
    fun handleConstraintViolationException(e: ConstraintViolationException): ResponseEntity<ExceptionResponse> {
        logger.error("누가 이상하게 요청 보냄: ${e.message}\n${e.stackTraceToString()}")

        val response = ExceptionResponse(HttpStatus.BAD_REQUEST, e.message ?: "올바르지 않은 접근입니다.")
        return ResponseEntity.badRequest().body(response)
    }

    // 주로 @RequestBody
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(e: MethodArgumentNotValidException): ResponseEntity<ExceptionResponse> {
        val response = ExceptionResponse(HttpStatus.BAD_REQUEST, e.message)
        return ResponseEntity.badRequest().body(response)
    }

    // 내가 지정한 ValidationException
    @ExceptionHandler(ValidationException::class)
    fun handleValidationException(e: ValidationException): ResponseEntity<ExceptionResponse> {
        val response = ExceptionResponse(HttpStatus.BAD_REQUEST, e.message ?: "잘못된 요청입니다.")
        return ResponseEntity.badRequest().body(response)
    }

    // 내가 던지는 예외
    @ExceptionHandler(BizException::class)
    fun handleBizException(e: BizException): ResponseEntity<ExceptionResponse> {
        val response = ExceptionResponse(e.status, e.message)
        return ResponseEntity(response, e.status)
    }

    // 내가 모르는 예외
    @ExceptionHandler(Exception::class)
    fun handleGenericException(e: Exception): ResponseEntity<ExceptionResponse> {
        logger.error("내가 처리하지 않은 에러: ${e.message}\n${e.stackTraceToString()}")
        return ResponseEntity.internalServerError().body(ExceptionResponse())
    }
}