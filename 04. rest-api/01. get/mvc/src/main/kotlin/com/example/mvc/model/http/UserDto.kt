package com.example.mvc.model.http

import com.fasterxml.jackson.annotation.JsonProperty

data class UserDto(
        var name: String?=null,
        var age: Int?=null,

        @field:JsonProperty("phone-number")
        var phoneNumber: String?=null
)