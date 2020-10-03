package com.example.todo.handler

import com.example.todo.controller.api.todo.TodoApiController
import com.example.todo.model.http.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.time.LocalDateTime
import javax.servlet.http.HttpServletRequest

@ControllerAdvice(basePackageClasses = [TodoApiController::class])
class TodoApiControllerAdvice {


    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun methodArgumentNotValidException(e: MethodArgumentNotValidException, request: HttpServletRequest): ResponseEntity<ErrorResponse> {
        val errors = mutableListOf<com.example.todo.model.http.Error>()

        e.bindingResult.allErrors.forEach { errorObject ->

            com.example.todo.model.http.Error().apply {
                this.field = (errorObject as FieldError).field
                this.message = errorObject.defaultMessage
                this.value = errorObject.rejectedValue
            }.apply {
                errors.add(this)
            }
        } // for each end


        val errorResponse = ErrorResponse().apply {
            this.resultCode = "FAIL"
            this.httpStatus = HttpStatus.BAD_REQUEST.value().toString()
            this.httpMethod = request.method
            this.message = ""
            this.path = request.requestURI
            this.timestamp = LocalDateTime.now()
            this.errors = errors
        }
        return ResponseEntity.badRequest().body(errorResponse)
    }


}