package com.intranet.sample.tobe.domain.board

import org.springframework.http.HttpStatus

object BoardError {
    val NOT_EMPTY_USERNAME = Pair(HttpStatus.BAD_REQUEST, "작성자를 입력해주세요.")
    val NOT_EMPTY_PASSWORD = Pair(HttpStatus.BAD_REQUEST, "비밀번호를 입력해주세요.")
    val NOT_EMPTY_TITLE = Pair(HttpStatus.BAD_REQUEST, "제목을 입력해주세요.")
    val NOT_EMPTY_CONTENT = Pair(HttpStatus.BAD_REQUEST, "내용을 입력해주세요.")

    val NOT_FOUND = Pair(HttpStatus.NOT_FOUND, "해당 게시물을 찾을 수 없습니다.")
    val WRONG_PASSWORD = Pair(HttpStatus.FORBIDDEN, "비밀번호가 올바르지 않습니다.")
    val ALREADY_DELETED = Pair(HttpStatus.CONFLICT, "이미 삭제된 게시물입니다.")

    // 기타 등등 ...

}