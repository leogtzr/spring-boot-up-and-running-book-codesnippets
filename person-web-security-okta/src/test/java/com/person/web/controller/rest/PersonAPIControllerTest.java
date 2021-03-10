package com.person.web.controller.rest;

import com.person.web.domain.Person;
import com.person.web.repository.PersonRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@WebFluxTest(controllers = PersonAPIController.class)
class PersonAPIControllerTest {

    @MockBean
    private PersonRepository personRepository;

    private Person p1;
    private Person p2;

    @BeforeEach
    void setUp(final ApplicationContext ctx) {
        // setup the persons:
        this.p1 = new Person();
        this.p1.setAge(30);
        this.p1.setName("Leonardo");
        this.p1.setId(30L);

        this.p2 = new Person();
        this.p2.setAge(25);
        this.p2.setName("Brenda");
        this.p2.setId(25L);

        Mockito
                .when(this.personRepository.findAll())
                .thenReturn(
                        List.of(this.p1, this.p2)
                );
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void persons(final @Autowired WebTestClient webTestClient) {
//        webTestClient.get().uri("/api/persons")
//                .exchange()
//                .expectStatus()
//                .isOk()
//        ;

        final List<Person> responseBody = webTestClient.get().uri("/api/persons")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Person.class)
                .returnResult()
                .getResponseBody();

        // System.out.println(responseBody);
        assertEquals(List.of(this.p1, this.p2), responseBody);
    }
}