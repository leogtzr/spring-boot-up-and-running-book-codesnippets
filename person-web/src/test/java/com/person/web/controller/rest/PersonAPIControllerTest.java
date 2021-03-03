package com.person.web.controller.rest;

import com.person.web.repository.PersonRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.*;

@WebFluxTest(controllers = PersonAPIController.class)
class PersonAPIControllerTest {

    @MockBean
    private PersonRepository personRepository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void persons(final @Autowired WebTestClient webTestClient) {
        webTestClient.get().uri("/api/persons")
                .exchange()
                .expectStatus()
                .isOk()
        ;
    }
}