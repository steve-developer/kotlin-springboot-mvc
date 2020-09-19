package com.springboot.mvc.controller.api.put

import com.springboot.mvc.dto.UserDto
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class PutApiController {

    @PutMapping("/put-mapping")
    fun pubMapping(@RequestBody userDto: UserDto){
        println("put mapping")
    }
}