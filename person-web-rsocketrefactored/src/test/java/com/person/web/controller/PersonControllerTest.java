package com.person.web.controller;

import com.person.web.domain.Person;
import com.person.web.service.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

// Slicing ...
@WebFluxTest(controllers = {PersonController.class})
public class PersonControllerTest {

    @Autowired
    private WebTestClient client;

    @MockBean
    private PersonService service;

    @MockBean
    private RSocketRequester requester;

    private Person person1;
    private Person person2;
    private Person person3;

    @BeforeEach
    public void setUp(final ApplicationContext context) {
        this.person1 = new Person(1L, 30, "Leo");
        this.person2 = new Person(2L, 25, "Brenda");
        this.person3 = new Person(3L, 50, "Edgar");

        Mockito.when(this.service.getAllPersons())
                .thenReturn(Flux.just(this.person1, this.person2, this.person3))
                ;

        Mockito.when(this.service.findById(1L)).thenReturn(Mono.just(this.person1));
        Mockito.when(this.service.findById(2L)).thenReturn(Mono.just(this.person2));
        Mockito.when(this.service.findById(3L)).thenReturn(Mono.just(this.person3));
    }

    @Test
    public void shouldReturnAllPersons() {
        StepVerifier.create(
                this.client.get()
                .uri("/person")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .returnResult(Person.class)
                .getResponseBody()
        ).expectNext(this.person1)
        .expectNext(this.person2)
        .expectNext(this.person3)
        .verifyComplete()
                ;
    }

}