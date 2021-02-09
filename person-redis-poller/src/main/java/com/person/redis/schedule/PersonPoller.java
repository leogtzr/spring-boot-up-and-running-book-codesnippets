package com.person.redis.schedule;

import com.person.redis.domain.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@EnableScheduling
@Component
@Slf4j
public class PersonPoller {

    private final RedisConnectionFactory connectionFactory;
    private final WebClient client = WebClient.create("http://localhost:8081/persons");

    @Autowired
    private PersonRepository repository;

    public PersonPoller(RedisConnectionFactory connectionFactory, PersonRepository repository) {
        this.connectionFactory = connectionFactory;
        this.repository = repository;
    }

    @Scheduled(fixedDelay = 1000)
    public void pollPersons() {
        log.info("I am running ... ");
        this.connectionFactory.getConnection().serverCommands().flushDb();

        this.client.get()
                .retrieve()
                .bodyToFlux(Person.class)
                .toStream()
                .forEach(this.repository::save)
        ;


        this.repository.findAll().forEach(System.out::println);
    }

}
