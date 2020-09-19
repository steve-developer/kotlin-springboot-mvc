package com.springboot.mvc.controller.page

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class PageController {

    @GetMapping("/main")
    fun main(): String {
        if(true){
            throw Exception("exception run")
        }
        return "index.html"
    }
}