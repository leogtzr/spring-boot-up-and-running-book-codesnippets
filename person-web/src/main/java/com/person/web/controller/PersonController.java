package com.person.web.controller;

import com.person.web.domain.Person;
import com.person.web.repository.PersonRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.function.client.WebClient;

@Controller
@RequiredArgsConstructor
public class PersonController {

    @NonNull
    private final PersonRepository personRepository;

    private final WebClient client = WebClient.create("http://localhost:8081/persons");

    @GetMapping("/person")
    public String getCurrentPerson(final Model model) {
        this.personRepository.deleteAll();

        client.get()
                .retrieve()
                .bodyToFlux(Person.class)
                .toStream()
                .forEach(this.personRepository::save)
                ;

        model.addAttribute("persons", this.personRepository.findAll());

        return "persons";
    }

}
