package com.person.web.repository;

import com.person.web.domain.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

    private Person p1;
    private Person p2;

    @BeforeEach
    void setUp() {
        this.p1 = new Person(1L, 30, "Leo");
        this.p2 = new Person(2L, 25, "Brenda");
        this.personRepository.saveAll(List.of(this.p1, this.p2));
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void testFindAll() {
        assertEquals(List.of(this.p1, this.p2), this.personRepository.findAll());
    }

}