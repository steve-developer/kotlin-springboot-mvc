package com.springboot.mvc.controller.api.get

import com.springboot.mvc.dto.UserDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class GetApiController {

    @GetMapping("get-mapping")
    fun getMapping(){
        println("get mapping")
    }

    @RequestMapping(method = [RequestMethod.GET], path = ["request-mapping"])
    fun requestMapping(){
        println("request mapping")
    }

    @GetMapping("get-mapping/path-variable/{name}")
    fun getMappingPathVariable(@PathVariable name: String): String {
        println(name)
        return name
    }

    @RequestMapping(method = [RequestMethod.GET], path = ["request-mapping/path-variable/{age}/{name}"])
    fun requestMappingPathVariable(@PathVariable age: Int, @PathVariable("name") n: String): String {
        println(age)
        println(n)
        return n + age
    }

    @GetMapping(path = ["get-mapping/query-param"])
    fun getMappingQueryParam(@RequestParam(name = "name", required = false) name: String): ResponseEntity<String> {
        return ResponseEntity.ok(name)
    }

    @RequestMapping(method = [RequestMethod.GET], path = ["request-mapping/query-param"])
    fun requestMappingQueryParam(@RequestParam(required = true) age: Int){
        println(age)
    }

    @GetMapping("get-mapping/dto")
    fun getMappingDto(user: UserDto): UserDto {
        println(user)
        return user
    }
}