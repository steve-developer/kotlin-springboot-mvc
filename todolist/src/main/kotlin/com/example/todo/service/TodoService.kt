package com.example.todo.service

import com.example.todo.model.dto.TodoDto
import com.example.todo.model.entity.Todo
import com.example.todo.repository.TodoRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Service
class TodoService(
        val todoRepository: TodoRepository
) {

    fun create(todoDto: TodoDto): Todo {
        return Todo().apply {
            this.name = todoDto.name
            this.description = todoDto.description
            this.schedule = LocalDateTime.parse(todoDto.schedule, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))

        }.run {
            todoRepository.save(this)

        }
    }

    fun read(index: Int?): Todo? {
        return todoRepository.findOne(index)
    }

    fun all(): MutableList<Todo> {
        return todoRepository.findAll()
    }

    fun update(todoDto: TodoDto): Todo {
        return Todo().apply {
            this.name = todoDto.name
            this.description = todoDto.description
            this.schedule = LocalDateTime.parse(todoDto.schedule, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))

        }.run {
            todoRepository.save(this)
        }
    }

    fun delete(index: Int?) {
        todoRepository.delete(index)
    }
}