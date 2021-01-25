package com.sbur.domain;

import lombok.Data;

import java.util.UUID;

@Data
public class Coffee {

    private final String id;
    private String name;

    public Coffee(final String id, final String name) {
        this.id = id;
        this.name = name;
    }

    public Coffee(final String name) {
        this(UUID.randomUUID().toString(), name);
    }
}