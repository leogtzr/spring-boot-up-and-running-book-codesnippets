package com.person.controller;

import com.person.domain.Person;
import com.person.repository.PersonRepository;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/persons")
public class PersonController {

    private final PersonRepository personRepository;

    public PersonController(final PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping
    public Flux<Person> getPersons() {
        return this.personRepository.findAll();
    }

    @PostMapping
    public Mono<Person> createPerson(@RequestBody final Person person) {
        return this.personRepository.save(person);
    }

}
