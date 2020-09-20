package com.example.todo.config

import com.example.todo.db.TodoDataBase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AppConfig{

    @Bean(initMethod = "init")
    fun todoDataBase() : TodoDataBase {
        return TodoDataBase()
    }
}