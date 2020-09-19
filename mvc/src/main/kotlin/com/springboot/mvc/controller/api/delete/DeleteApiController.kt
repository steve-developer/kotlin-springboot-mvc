package com.springboot.mvc.controller.api.delete

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class DeleteApiController {

    @DeleteMapping("/delete-mapping/{id}")
    fun deleteMapping(@PathVariable id: Long){
        println(id)
    }
}