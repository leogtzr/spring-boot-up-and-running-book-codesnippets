package com.person.service.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@ToString
@EqualsAndHashCode
@Builder
// The annotation below are required if we want to use @Builder.
@NoArgsConstructor
@AllArgsConstructor
@Document
@JsonIgnoreProperties(ignoreUnknown = true)
public class Person {
    @Id
    private String id;
    private int age;
    private String name;
}
