package com.person.service.schedule;

import com.person.service.domain.Person;
import com.person.service.repository.PersonRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@EnableScheduling
@Component
@RequiredArgsConstructor
@Slf4j
public class PersonPoller {

    @NonNull
    private final PersonRepository repository;

    private final WebClient client = WebClient.create("http://localhost:8081/persons");

    @Scheduled(fixedRate = 1000)
    public void pollPersons() {
        // Commented out to avoid remove the records from data-${PLATFORM}.sql
        // this.repository.deleteAll();

        this.client.get()
                .retrieve()
                .bodyToFlux(Person.class)
                .toStream()
                .forEach(this.repository::save)
        ;

        this.repository.findAll().forEach(System.out::println);
    }

}
