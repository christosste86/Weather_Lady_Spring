package com.example.weatherladyspring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;

@Controller
public class MainController {

    @GetMapping("/hello")
    @ResponseBody
    public String sayHello(){
        return "Hello World";
    }

    @GetMapping("/hi")
    @ResponseBody
    public String sayHi(@RequestParam(name = "name", required = false) String firstName,
                        @RequestParam(name = "number", required = false, defaultValue = "13") Integer favoriteNumber)
    {
        return "Hi " + (Objects.isNull(firstName)? "friend" : firstName) +
                " your favorite number is " + favoriteNumber;
    }
}
