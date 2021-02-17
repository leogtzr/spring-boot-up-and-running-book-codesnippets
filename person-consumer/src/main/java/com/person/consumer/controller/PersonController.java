package com.person.consumer.controller;

import com.person.consumer.domain.Person;
import com.person.consumer.repository.PersonRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/persons")
public class PersonController {

    private final PersonRepository personRepository;

    public PersonController(final PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping
    public Iterable<Person> getPersons() {
        return this.personRepository.findAll();
    }

}
