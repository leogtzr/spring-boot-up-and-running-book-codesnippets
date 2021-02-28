package com.person.web.controller;

import com.person.web.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DebugController {

    private final PersonRepository personRepository;

    @GetMapping("/debug")
    public void debug() {
        this.personRepository.findAll().subscribe(p -> System.out.println(p));
    }

}
