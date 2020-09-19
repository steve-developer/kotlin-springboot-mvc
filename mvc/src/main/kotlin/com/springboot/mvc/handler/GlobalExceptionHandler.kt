package com.springboot.mvc.handler

import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(value = [Exception::class, RuntimeException::class])
    fun exception(e: Exception): String? {
        return "error.html"
    }

}