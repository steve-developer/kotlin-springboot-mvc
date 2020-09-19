package com.springboot.mvc.handler

import com.springboot.mvc.dto.ErrorDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice(basePackages = ["com.springboot.mvc.controller.api"])
class ApiControllerExceptionHandler {

    /*@ExceptionHandler(value = [MethodArgumentNotValidException::class])
    fun exceptionHandler(methodArgumentNotValidException: MethodArgumentNotValidException): ResponseEntity<ErrorDto> {
        println("ApiControllerExceptionHandler MethodArgumentNotValidException Exception 발생")
        val bindingResult = methodArgumentNotValidException.bindingResult
        val message = mutableListOf<String>()
        bindingResult.allErrors.forEach {
            message.add("${(it as FieldError).field } : ${it.defaultMessage}")
        }

        val errorDto = ErrorDto().apply {
            this.message = message
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDto)
    }*/
}