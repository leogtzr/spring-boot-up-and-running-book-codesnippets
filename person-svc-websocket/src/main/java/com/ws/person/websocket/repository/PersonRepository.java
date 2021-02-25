package com.ws.person.websocket.repository;

import com.ws.person.domain.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long> { }
