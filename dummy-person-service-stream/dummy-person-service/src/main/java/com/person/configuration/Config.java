package com.person.configuration;

import com.person.domain.Person;
import com.person.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Supplier;

@Configuration
@AllArgsConstructor
public class Config {

    private final PersonRepository personRepository;

    @Bean
    public Supplier<Iterable<Person>> reportPersons() {
        return () -> this.personRepository.findAll();
    }

}
