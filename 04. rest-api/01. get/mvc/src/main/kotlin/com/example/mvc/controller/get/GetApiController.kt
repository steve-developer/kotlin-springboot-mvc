package com.example.mvc.controller.get

import com.example.mvc.model.http.UserDto
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController             // 해당 클래스는 rest api controller 로 동작 한다.
@RequestMapping("api")  // http://localhost:8080/api
class GetApiController {

    @Autowired
    lateinit var objectMapper : ObjectMapper

    // GET http://localhost:8080/api/hello
    @RequestMapping(method = [RequestMethod.GET], path = ["hello"])
    fun hello(): String{
        return "hello kotlin"
    }

    @GetMapping(path = ["hello2"])
    fun hello2(): String{
        return "hello2 kotlin"
    }

    @GetMapping(path = ["/path-variable/{name}/{age}"])
    fun pathVariable(@PathVariable(value = "name") _name: String, @PathVariable(name = "age") _age: Int): String {
        var name = "name"
        println("name : ${_name}, age : ${_age}")
        return _name+" "+_age
    }

    // http://localhost:8080/api/query-param?name=steve&age=10&phone-number=01011112222
    @GetMapping(path = ["/query-param"])
    fun queryParam(@RequestParam(required = false) name: String?,
                   @RequestParam age: Int,
                   @RequestParam("phone-number") phoneNumber: String
    ): String {
        println("name : ${name} , age : ${age}, phone-number ${phoneNumber}")
        return name+" "+age+" "+phoneNumber
    }

    // http://localhost:8080/api/query-param?name=steve&age=10&phone-number=01011112222
    @GetMapping(path = ["/query-param/dto"])
    fun queryParamDto(@RequestParam map : Map<String,Any>): UserDto? {
        println(map)
        val userDto = jacksonObjectMapper().convertValue(map, UserDto::class.java)
        return userDto
    }
}