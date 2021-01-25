package com.sbur.controller;

import com.sbur.domain.Coffee;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RestApiDemoController {

    private List<Coffee> coffees = new ArrayList<>();

    public RestApiDemoController() {
        this.coffees.addAll(List.of(
                new Coffee("Café Cereza"),
                new Coffee("Café Ganador"),
                new Coffee("Café Lareño"),
                new Coffee("Café Três Pontas")
        ));
    }

    @RequestMapping(value = "/coffees", method = RequestMethod.GET)
    // or simply @GetMapping("/coffees")
    public Iterable<Coffee> getCoffees() {
        return this.coffees;
    }

}
