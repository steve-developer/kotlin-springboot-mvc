package com.example.todo.model.entity

import java.time.LocalDateTime

data class Todo(
        var index:Int?=null,                    // 일정 index
        var name: String?=null,                 // 일정이름
        var description: String?=null,          // 설명
        var schedule: LocalDateTime?=null,      // 일정시간
        var createdAt: LocalDateTime?=null,     // 생성시간
        var updatedAt: LocalDateTime?=null      // 수정시간
)