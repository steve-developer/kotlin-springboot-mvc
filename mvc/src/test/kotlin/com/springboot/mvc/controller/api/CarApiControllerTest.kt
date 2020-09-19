package com.springboot.mvc.controller.api

import com.fasterxml.jackson.databind.ObjectMapper
import com.springboot.mvc.controller.api.car.CarApiController
import com.springboot.mvc.dto.CarDto
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.time.LocalDateTime
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print

@WebMvcTest
@AutoConfigureMockMvc
class CarApiControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var objectMapper: ObjectMapper

    @Test
    fun postTest(){
        val car = CarDto().apply{
            this.name = "A4"
            this.number = 3000
            this.distance = 2900
            this.phoneNumber = "010-1234-5678"
            this.registerAt = LocalDateTime.now().minusDays(1)
            this.expireAt = LocalDateTime.now().plusDays(1)
            this.inspectionDateTime = "20200918223344"
        }

        val content = objectMapper.writeValueAsString(car)

        mockMvc.perform(
                put("/api/car")
                        .content(content)
                        .contentType("application/json")
                        .accept("application/json")
        ).andExpect(
                status().isOk
        ).andExpect(
                jsonPath("\$.name").value(car.name.toString())
        )
    }

    @Test
    fun postFailTest(){
        val car = CarDto().apply{
            this.name = "A4"
            this.number = 3000
            this.distance = 2900
            this.phoneNumber = "01012345678"
            this.registerAt = LocalDateTime.now().minusDays(1)
            this.expireAt = LocalDateTime.now().plusDays(1)
            this.inspectionDateTime = "20200918223344"
        }

        val content = objectMapper.writeValueAsString(car)

        mockMvc.perform(
                put("/api/car")
                        .content(content)
                        .contentType("application/json")
                        .accept("application/json")
        ).andExpect(
                status().isBadRequest
        ).andExpect(
                jsonPath("\$.result.code").value("error")
        )
        .andExpect(
                jsonPath("\$.result.description[0].field").value("phoneNumber")
        )
        .andDo(print())
    }

    @Test
    fun putTest(){

    }

}