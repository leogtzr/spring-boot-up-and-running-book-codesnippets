package com.sbur.controller;

import com.sbur.domain.Greeting;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/saludo")
public class GreetingController2 {

    private final Greeting greeting;

    public GreetingController2(final Greeting greeting) {
        this.greeting = greeting;
    }

    @GetMapping
    public String getGreeting() {
        return this.greeting.getName();
    }

    @GetMapping("/coffee")
    public String getNameAndCoffee() {
        return this.greeting.getCoffee();
    }

}
