package com.WebApplication.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @RequestMapping("/")
//    @ResponseBody
    public String welcome() {
        return "Welcome to this Web...!";
    }

    @RequestMapping("/about")
    public String about() {
        return "This is about page";
    }
}
