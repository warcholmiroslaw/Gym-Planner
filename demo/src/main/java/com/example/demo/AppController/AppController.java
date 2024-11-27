package com.example.demo.AppController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

    @GetMapping
    public String hello() {
        return "powinno dzialac !!!!!!!!!!!";
    }
}
