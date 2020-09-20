package com.example.todo.config

import com.example.todo.db.TodoDataBase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.validation.Validation
import javax.validation.Validator

@Configuration
class AppConfig{

    @Bean(initMethod = "init")
    fun todoDataBase() : TodoDataBase {
        return TodoDataBase()
    }

    @Bean(name = ["validator"])
    fun validator(): Validator {
        return Validation.buildDefaultValidatorFactory().validator
    }
}