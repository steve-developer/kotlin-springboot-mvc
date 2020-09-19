package com.springboot.mvc.controller.api.post

import com.springboot.mvc.dto.UserDto
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class PostApiController {

    @PostMapping("/post-mapping")
    fun postMapping(){
        println("post mapping")
    }

    @RequestMapping(method = [RequestMethod.POST], path = ["request-mapping"])
    fun requestMapping(){
        println("request mapping")
    }

    @PostMapping("/post-mapping/dto")
    fun postMappingDto(@RequestBody user: UserDto){
        println(user)
    }

    @RequestMapping(method = [RequestMethod.POST], path = ["request-mapping/dto"])
    fun requestMappingDto(@RequestBody user: UserDto?){
        println(user)
    }
}