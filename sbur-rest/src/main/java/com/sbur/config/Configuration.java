package com.sbur.config;

import com.sbur.domain.Droid;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    @ConfigurationProperties(prefix = "droid")
    public Droid createDroid() {
        return new Droid();
    }

}
