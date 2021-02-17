package com.person.configuration;

import com.person.domain.Person;
import com.person.repository.PersonRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Configuration
@AllArgsConstructor
@Slf4j
public class Config {

    private final PersonRepository personRepository;

    @Bean
    public Supplier<Iterable<Person>> reportPersons() {
        return () -> {
            log.info("Something ... ");
            final Iterable<Person> all = this.personRepository.findAll();

            final List<Iterable<Person>> data = Stream.of(all).collect(Collectors.toList());
            data.forEach(System.out::println);

            return all;
        };
    }

}
