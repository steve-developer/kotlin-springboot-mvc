package com.example.todo.model.dto

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import javax.validation.Validator

@SpringBootTest
class TodoDtoTest {

    @Autowired
    @Qualifier(value = "validator")
    lateinit var validator: Validator

    @Test
    fun todoDtoTest(){
        // given
        val dto = TodoDto().apply {
            this.name = "name"
            this.description = "description"
            this.schedule = "schedule"
        }

        // when
        val result = validator.validate(dto)


        // then
        Assertions.assertEquals(result.size, 0)
    }

    @Test
    fun todoDtoFailTest(){
        // given
        val dto = TodoDto().apply {
            this.index = -1
            this.name = ""
            this.description = ""
            this.schedule = ""
        }

        // when
        val result = validator.validate(dto)
        /*
        result.forEach {
            println(it.propertyPath)
            println(it.message)
        }
        */

        // then
        Assertions.assertEquals(result.size, 4)
    }
}