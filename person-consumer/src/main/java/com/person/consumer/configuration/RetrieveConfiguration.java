package com.person.consumer.configuration;

import com.person.consumer.domain.Person;
import com.person.consumer.repository.PersonRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.function.Consumer;

@Configuration
@AllArgsConstructor
@Slf4j
public class RetrieveConfiguration {

    private final PersonRepository personRepository;

    @Bean
    public Consumer<List<Person>> retrievePersons() {
        return persons -> {
            log.info("------- Consuming --------");
            this.personRepository.deleteAll();
            this.personRepository.saveAll(persons);
            this.personRepository.findAll().forEach(System.out::println);
        };
    }

}
