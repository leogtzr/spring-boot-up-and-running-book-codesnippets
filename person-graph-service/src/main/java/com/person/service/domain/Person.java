package com.person.service.domain;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Data
@ToString
@EqualsAndHashCode
@Builder
// The annotation below are required if we want to use @Builder.
@NoArgsConstructor
@AllArgsConstructor
@Node
@JsonIgnoreProperties(ignoreUnknown = true)
public class Person {

    @Id @GeneratedValue
    private Long neoId;

    private Long id;

    private int age;
    private String name;

}
