package com.example.todo.model.dto

import com.example.todo.annotation.StringDateTimeFormat
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.PositiveOrZero

@ApiModel(description = "일정 등록 요청", value = "request model")
data class TodoDto(

        @field:ApiModelProperty(
                value = "DB Index",
                example = "0",
                required = false
        )
        @field:PositiveOrZero
        var index: Int?=null,

        @field:ApiModelProperty(
                value = "일정명",
                example = "13시 점심 약속",
                required = true
        )
        @field:NotEmpty
        var name: String?=null,

        @field:ApiModelProperty(
                value = "일정 설명",
                example = "친구랑 명동",
                required = true
        )
        @field:NotEmpty
        var description: String?=null,

        @field:ApiModelProperty(
                value = "일정 시간",
                example = "2020-03-13 13:00:00",
                required = true
        )
        @field:NotEmpty
        @field:StringDateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        var schedule: String?=null
)