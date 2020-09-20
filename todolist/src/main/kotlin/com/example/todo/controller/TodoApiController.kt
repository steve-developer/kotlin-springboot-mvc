package com.example.todo.controller

import com.example.todo.model.dto.Description
import com.example.todo.model.dto.ErrorDto
import com.example.todo.model.dto.TodoDto
import com.example.todo.model.entity.Todo
import com.example.todo.service.TodoService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/todo")
class TodoApiController(
        val todoService: TodoService
) {

    @PostMapping("")
    fun create(@Valid @RequestBody todoDto: TodoDto): ResponseEntity<Todo> {
        return ResponseEntity.ok(todoService.create(todoDto))
    }

    @GetMapping("/{index}")
    fun read(@PathVariable(required = false) index: Int?): Todo? {
        return todoService.read(index)
    }

    @GetMapping("/all")
    fun all(): MutableList<Todo> {
        return todoService.all()
    }

    @PutMapping("")
    fun update(@Valid @RequestBody todoDto: TodoDto): Todo {
        return todoService.update(todoDto)
    }

    @DeleteMapping("")
    fun delete(@RequestParam(required = true) index: Int?){
        return todoService.delete(index)
    }


    @ExceptionHandler(value = [MethodArgumentNotValidException::class])
    fun exceptionHandler(methodArgumentNotValidException: MethodArgumentNotValidException): ResponseEntity<ErrorDto> {
        val bindingResult = methodArgumentNotValidException.bindingResult
        val message = mutableListOf<Description>()
        bindingResult.allErrors.forEach {
            message.add(Description((it as FieldError).field,it.defaultMessage))
        }

        val errorDto = ErrorDto().apply {
            this.result = com.example.todo.model.dto.Result().apply {
                this.code = "error"
                this.description = message
            }
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDto)
    }

}