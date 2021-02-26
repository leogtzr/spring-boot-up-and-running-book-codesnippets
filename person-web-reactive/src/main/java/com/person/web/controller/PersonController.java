package com.person.web.controller;

import com.person.web.domain.Person;
import com.person.web.repository.PersonRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Controller
@RequiredArgsConstructor
public class PersonController {

    @NonNull
    private final PersonRepository personRepository;

    private final WebClient client = WebClient.create("http://localhost:8081/persons");

    @GetMapping("/person")
    public String getPersons(final Model model) {
        final Flux<Person> aircraftFlux = this.personRepository.deleteAll()
                .thenMany(client.get()
                        .retrieve()
                        .bodyToFlux(Person.class)
                        .flatMap(this.personRepository::save));

        final Flux<Person> all = this.personRepository.findAll();
        model.addAttribute("persons", all);

        return "persons";
    }

}
