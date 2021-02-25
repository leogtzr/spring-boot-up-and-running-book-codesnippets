package com.ws.person.domain;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Id;

@Data
@ToString
@EqualsAndHashCode
@Builder
// The annotation below are required if we want to use @Builder.
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Person {

    @Id
    private Long id;

    private int age;
    private String name;

}
