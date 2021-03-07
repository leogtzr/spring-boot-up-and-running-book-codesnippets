package com.person.web.controller.rest;

import com.person.web.domain.Person;
import com.person.web.repository.PersonRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PersonAPIController {

    @NonNull
    private final PersonRepository personRepository;

    @GetMapping("persons")
    public Iterable<Person> persons() {
        return this.personRepository.findAll();
    }

    @GetMapping("personsadmin")
    public Iterable<Person> personsAdmin() {
        return this.personRepository.findAll();
    }

}
