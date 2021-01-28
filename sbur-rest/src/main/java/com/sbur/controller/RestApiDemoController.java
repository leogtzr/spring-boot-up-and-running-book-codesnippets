package com.sbur.controller;

import com.sbur.domain.Coffee;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/coffees")
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
    @GetMapping
    public Iterable<Coffee> getCoffees() {
        return this.coffees;
    }

    @GetMapping("/{id}")
    public Optional<Coffee> getCoffeeById(@PathVariable  final String id) {
        return this.coffees.stream().filter(coffee -> coffee.getId().equals(id)).findFirst();
    }

    @PostMapping
    public Coffee createCoffee(@RequestBody final Coffee coffee) {
        this.coffees.add(coffee);
        return coffee;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Coffee> updateCoffee(@PathVariable final String id, @RequestBody final Coffee coffee) {
        int index = -1;
        for (final Coffee cf : this.coffees) {
            if (cf.getId().equals(id)) {
                index = this.coffees.indexOf(coffee);
                this.coffees.set(index, coffee);
            }
        }

//        return index == -1 ?
//                ResponseEntity.status(HttpStatus.CREATED).body(coffee) : ResponseEntity.ok(coffee)
//                ;
        return (index == -1) ?
                new ResponseEntity<>(createCoffee(coffee), HttpStatus.CREATED) :
                new ResponseEntity<>(coffee, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteCoffee(@PathVariable final String id) {
        this.coffees.removeIf(cf -> cf.getId().equals(id));
    }

}
