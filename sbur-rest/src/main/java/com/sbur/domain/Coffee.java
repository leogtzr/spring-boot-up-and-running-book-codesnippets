package com.sbur.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Data
// @EqualsAndHashCode
// @Entity(name = "XXX") just in case the class name doesn't match what we have in the DB.
@Entity
@ToString
public class Coffee {

    @Id
    private String id;
    private String name;

    public Coffee(final String id, final String name) {
        this.id = id;
        this.name = name;
    }

    public Coffee(final String name) {
        this(UUID.randomUUID().toString(), name);
    }

    public Coffee() {
        this(UUID.randomUUID().toString(), "");
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }
}