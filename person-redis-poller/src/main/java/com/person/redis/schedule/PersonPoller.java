package com.person.redis.schedule;

import com.person.redis.domain.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@EnableScheduling
@Component
@Slf4j
public class PersonPoller {

    private final RedisConnectionFactory connectionFactory;
    private final RedisOperations<String, Person> redisOperations;

    private WebClient client = WebClient.create("http://localhost:8081/persons");

    public PersonPoller(
            final RedisConnectionFactory connectionFactory
            , final RedisOperations<String, Person> redisOperations) {
        this.connectionFactory = connectionFactory;
        this.redisOperations = redisOperations;
    }

    @Scheduled(fixedDelay = 1000)
    public void pollPersons() {
        this.connectionFactory.getConnection().serverCommands().flushDb();

        client.get()
                .retrieve()
                .bodyToFlux(Person.class)
                // .filter(plane -> !plane.getReg().isEmpty())
                .toStream()
                .forEach(person -> redisOperations.opsForValue().set(person.getName(), person));

        redisOperations.opsForValue()
                .getOperations()
                .keys("*")
                .forEach(person -> System.out.println(redisOperations.opsForValue().get(person)))
        ;
    }


}
