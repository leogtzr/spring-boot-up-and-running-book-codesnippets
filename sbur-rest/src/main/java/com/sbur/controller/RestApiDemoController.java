package com.sbur.controller;

import com.sbur.domain.Coffee;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
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

    //@RequestMapping(value = "/coffees", method = RequestMethod.GET)
    @GetMapping("/coffees")
    public Iterable<Coffee> getCoffees() {
        return this.coffees;
    }

    @GetMapping("/coffees/{id}")
    public Optional<Coffee> getCoffeeById(@PathVariable  final String id) {
        return this.coffees.stream().filter(coffee -> coffee.getId().equals(id)).findFirst();
    }

    @PostMapping("/coffees")
    public Coffee createCoffee(final Coffee coffee) {
        this.coffees.add(coffee);
        return coffee;
    }

    @PutMapping("/coffees/{id}")
    public Coffee updateCoffee(@PathVariable final String id, @RequestBody final Coffee coffee) {
        int index = -1;
        for (final Coffee cf : this.coffees) {
            if (cf.getId().equals(id)) {
                index = this.coffees.indexOf(coffee);
                this.coffees.set(index, coffee);
            }
        }

        return index == -1 ? this.createCoffee(coffee) : coffee;
    }

    @DeleteMapping("/coffees/{id}")
    public void deleteCoffee(@PathVariable final String id) {
        this.coffees.removeIf(cf -> cf.getId().equals(id));
    }

}
