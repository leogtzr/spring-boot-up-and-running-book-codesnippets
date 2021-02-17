package com.person.consumer.repository;

import com.person.consumer.domain.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long> { }
