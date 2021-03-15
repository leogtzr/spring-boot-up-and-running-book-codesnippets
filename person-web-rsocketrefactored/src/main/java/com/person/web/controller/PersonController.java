package com.person.web.controller;

import com.person.web.domain.Person;
import com.person.web.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import org.springframework.messaging.rsocket.RSocketRequester;

@Controller
// @RequiredArgsConstructor
@Slf4j
public class PersonController {

    private final PersonService personService;
    private final RSocketRequester requester;

    public PersonController(final PersonService personService, final RSocketRequester requester) {
        this.personService = personService;
        this.requester = requester;
    }

    private final WebClient client = WebClient.create("http://localhost:8081/persons");

    @GetMapping("/person")
    public String getPersons(final Model model) {
        model.addAttribute("persons", this.personService.getAllPersons());

        return "persons";
    }

    @ResponseBody
    // Returning a stream of Server Sent Events (SSE)
    @GetMapping(value = "/personstream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Person> getPersonsStream() {
        return this.requester.route("personstream")
                .data("Requesting persons")
                .retrieveFlux(Person.class)
                ;
    }

}
