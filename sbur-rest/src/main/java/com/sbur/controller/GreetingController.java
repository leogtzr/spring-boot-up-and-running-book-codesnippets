package com.sbur.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/greeting")
public class GreetingController {

    // default value is Sara.
    @Value("${greeting-name: Sara}")
    private String name;

    @Value("${greeting-coffee: ${greeting-name} is drinking Café Ganador}")
    private String coffee;

    @GetMapping
    public String getGreeting() {
        return name;
    }

    @GetMapping("/coffee")
    public String getNameAndCoffee() {
        return this.coffee;
    }

}
