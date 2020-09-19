package com.springboot.mvc

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext

@SpringBootTest
class MvcApplicationTests {

    @Autowired
    lateinit var applicationContext: ApplicationContext

    @Test
    fun contextLoads() {
        Assertions.assertNotNull(applicationContext)
    }

}
