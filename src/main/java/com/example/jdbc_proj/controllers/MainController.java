package com.example.jdbc_proj.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class MainController {

    @GetMapping("/")
    public String greeting() {
        String val = System.getProperty("max.dick");
        return "Hello, you are on the main page! " + val;
    }
}
