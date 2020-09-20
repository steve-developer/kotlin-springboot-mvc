package com.example.todo.controller

import com.example.todo.model.dto.TodoDto
import com.example.todo.repository.TodoRepository
import com.example.todo.service.TodoService
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
class TodoApiControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var objectMapper: ObjectMapper

    @Autowired
    lateinit var todoService: TodoService

    @Autowired
    lateinit var todoRepository: TodoRepository

    @Test
    fun createTest(){
        // given
        todoRepository.todoDataBase.init()
        val request = TodoDto().apply {
            this.name = "테스트 1"
            this.description = "테스트 1"
            this.schedule = "2020-09-20 11:00:00"
        }.run {
            objectMapper.writeValueAsString(this)
        }

        // when
        mockMvc.perform(
                post("/api/todo")
                        .content(request)
                        .characterEncoding("UTF-8")
                        .contentType("application/json")
                        .accept("application/json")
        ).andExpect(
                status().isOk
        ).andExpect(
                jsonPath("\$.name").value("테스트 1")
        ).andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun read(){
        // given
        todoRepository.todoDataBase.init()
        TodoDto().apply {
            this.name = "테스트 1"
            this.description = "테스트 1"
            this.schedule = "2020-09-20 11:00:00"
        }.run {
            todoService.create(this)
        }

        // when
        mockMvc.perform(
                get("/api/todo/0")
                        .contentType("application/json")
                        .characterEncoding("UTF-8")
                        .accept("application/json")
        ).andExpect(
                status().isOk
        ).andExpect(
                jsonPath("\$.name").value("테스트 1")
        ).andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun update(){
        // given
        todoRepository.todoDataBase.init()
        val todo = TodoDto().apply {
            this.name = "테스트 1"
            this.description = "테스트 1"
            this.schedule = "2020-09-20 11:00:00"
        }.run {
            todoService.create(this)
        }


        val content = TodoDto().apply {
            this.index = 0
            this.name = "업데이트 테스트"
            this.description = "업데이트 테스트"
            this.schedule = "2020-09-20 13:00:00"
        }.run {
            objectMapper.writeValueAsString(this)
        }

        // when
        mockMvc.perform(
                put("/api/todo")
                        .content(content)
                        .characterEncoding("UTF-8")
                        .contentType("application/json")
                        .accept("application/json")
        ).andExpect(
                status().isOk
        ).andExpect(
                jsonPath("\$.name").value("업데이트 테스트")
        ).andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun delete(){
        // given
        todoRepository.todoDataBase.init()
        TodoDto().apply {
            this.name = "테스트 1"
            this.description = "테스트 1"
            this.schedule = "2020-09-20 11:00:00"
        }.run {
            todoService.create(this)
        }

        // when
        mockMvc.perform(
                delete("/api/todo?index=0")
        ).andExpect(
                status().isOk
        ).andDo(MockMvcResultHandlers.print())
    }
}