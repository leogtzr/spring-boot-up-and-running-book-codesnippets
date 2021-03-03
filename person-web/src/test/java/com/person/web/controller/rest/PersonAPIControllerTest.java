package com.person.web.controller.rest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PersonAPIControllerTest {

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
                .expectBody(Iterable.class)
        .returnResult()
        .getResponseBody()
        .iterator()
        .hasNext()
        ;
    }
}