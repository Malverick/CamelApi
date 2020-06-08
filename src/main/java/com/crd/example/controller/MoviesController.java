package com.crd.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
public class MoviesController {

    @GetMapping("/messages")
    public final String getMessages() {
//        log.info("inside Spring Controller");
        return "Hello";
    }
}
