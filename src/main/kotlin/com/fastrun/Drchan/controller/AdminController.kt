package com.fastrun.TempCollection.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.servlet.ModelAndView

@Controller
class AdminController {
    @GetMapping("/admin/index")
    fun AdminIndex(): ModelAndView {
        var view = ModelAndView()
        view.viewName = "admin/index"
        return view
    }
}