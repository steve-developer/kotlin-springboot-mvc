package com.springboot.mvc.controller.api.car

import com.springboot.mvc.dto.CarDto
import com.springboot.mvc.dto.Description
import com.springboot.mvc.dto.ErrorDto
import com.springboot.mvc.dto.Result
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.*
import javax.validation.Valid


@RestController
@RequestMapping("/api/car")
class CarApiController {

    @PutMapping("")
    fun read(@Valid @RequestBody carDto: CarDto?
             //, bindingResult: BindingResult
    ): ResponseEntity<CarDto>? {
        /*if(bindingResult.hasErrors()){
            val message = mutableListOf<String>()

            bindingResult.allErrors.forEach {
                message.add(it.defaultMessage.toString())
            }

            val errorDto = ErrorDto().apply {
                this.message = message
            }

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDto)
        }*/

        return carDto?.let { ResponseEntity.ok(it) }
    }


    @PostMapping("")
    fun create(@Valid
               @RequestBody
               carDto: CarDto?
    ): CarDto? {
        return carDto
    }


    @ExceptionHandler(value = [MethodArgumentNotValidException::class])
    fun exceptionHandler(methodArgumentNotValidException: MethodArgumentNotValidException): ResponseEntity<ErrorDto> {
        println("CarApiController MethodArgumentNotValidException Exception 발생")
        val bindingResult = methodArgumentNotValidException.bindingResult
        val message = mutableListOf<Description>()
        bindingResult.allErrors.forEach {
            message.add(Description((it as FieldError).field,it.defaultMessage))
        }

        val errorDto = ErrorDto().apply {
            this.result = Result().apply {
                this.code = "error"
                this.description = message
            }
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDto)
    }

}