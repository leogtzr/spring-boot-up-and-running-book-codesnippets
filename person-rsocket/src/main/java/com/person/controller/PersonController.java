package com.person.controller;

import com.person.domain.Person;
import com.person.repository.PersonRepository;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Controller
@RequestMapping("/persons")
public class PersonController {

    private final PersonRepository personRepository;

    public PersonController(final PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @ResponseBody
    @GetMapping("/persons")
    public Flux<Person> getPersons() {
        return this.personRepository.findAll();
    }

//    @PostMapping
//    public Mono<Person> createPerson(@RequestBody final Person person) {
//        return this.personRepository.save(person);
//    }

    @MessageMapping("personstream")
    public Flux<Person> getPersonStream() {
        return this.personRepository
                .findAll()
                .concatWith(Flux.interval(Duration.ofSeconds(1))
                        .flatMap(
                                l -> this.personRepository.findAll()
                        )
                );
    }

}
