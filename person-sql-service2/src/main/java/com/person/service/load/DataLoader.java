package com.person.service.load;

import com.person.service.domain.Person;
import com.person.service.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@AllArgsConstructor
@Component
public class DataLoader {

    private final PersonRepository personRepository;

    @PostConstruct
    public void load() {
        this.personRepository.deleteAll();

        this.personRepository.save(Person.builder().age(30).name("Leonardo Gutiérrez Ramírez").build());
        this.personRepository.save(Person.builder().age(25).name("Brenda").build());
        this.personRepository.save(Person.builder().age(26).name("Alejandra").build());
    }

}
