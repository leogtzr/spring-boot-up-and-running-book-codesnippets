package com.person.web.domain;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@ToString
@EqualsAndHashCode
@Builder
// The annotation below are required if we want to use @Builder.
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Person {
    private Long id;
    private int age;
    private String name;
}
