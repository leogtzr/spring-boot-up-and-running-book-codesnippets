package com.sbur.domain;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ToString
@EqualsAndHashCode
@ConfigurationProperties(prefix = "greeting")
public class Greeting {

    private String name;
    private String coffee;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getCoffee() {
        return coffee;
    }

    public void setCoffee(final String coffee) {
        this.coffee = coffee;
    }

}
