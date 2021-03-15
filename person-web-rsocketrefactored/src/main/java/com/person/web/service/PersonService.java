package com.person.web.service;

import com.person.web.domain.Person;
import com.person.web.repository.PersonRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final WebClient client = WebClient.create("http://localhost:8081/persons");

    public PersonService(final PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Flux<Person> getAllPersons() {
        return this.personRepository.deleteAll()
                .thenMany(client.get()
                        .retrieve()
                        .bodyToFlux(Person.class)
                        .flatMap(this.personRepository::save));
    }

    public Mono<Person> findById(final Long id) {
        return this.personRepository.findById(id);
    }

}
