package com.example.todo.model.dto

data class ErrorDto(
        var result: Result?=null
)

data class Result(
        var code: String?=null,
        var description: MutableList<Description>?=null
)

data class Description(
        val field : String?=null,
        var message : String?=null
)