package com.springboot.mvc.controller.page

import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MockMvcBuilder
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.setup.MockMvcBuilders

@AutoConfigureMockMvc
@WebMvcTest(controllers = [PageController::class])
class PageControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun successTest(){
        mockMvc.perform(
                get("/main"))
                .andExpect { status().isOk }
                .andExpect(
                        view().name("main.html")
                )
                .andDo(print())
    }

    @Test
    fun failTest(){
        mockMvc.perform(
                get("/main"))
                .andExpect { status().isOk }
                .andExpect(
                        view().name("error.html")
                )
                .andDo(print())
    }
}