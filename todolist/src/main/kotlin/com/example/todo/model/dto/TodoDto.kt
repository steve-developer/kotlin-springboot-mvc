package com.example.todo.model.dto

import com.example.todo.annotation.StringDateTimeFormat
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.PositiveOrZero

data class TodoDto(
        @field:PositiveOrZero
        var index: Int?=null,

        @field:NotEmpty
        var name: String?=null,

        @field:NotEmpty
        var description: String?=null,

        @field:NotEmpty
        @field:StringDateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        var schedule: String?=null
)