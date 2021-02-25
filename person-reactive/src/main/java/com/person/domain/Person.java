package com.person.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

// @Entity
@Data
@ToString
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
public class Person {
//    @Id
//    @GeneratedValue
    private Long id;

    private int age;
    private String name;
}
