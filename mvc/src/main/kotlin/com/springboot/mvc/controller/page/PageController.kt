package com.springboot.mvc.controller.page

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/")
class PageController {

    @GetMapping("/main")
    fun main(): String {
        return "index.html"
    }
}