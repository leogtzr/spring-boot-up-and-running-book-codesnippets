package com.person.web.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.rsocket.RSocketRequester;

@Configuration
public class RSocketRequesterConfig {

    @Bean
    public RSocketRequester requester(final RSocketRequester.Builder builder) {
        return builder.tcp("localhost", 8091);
    }

}
