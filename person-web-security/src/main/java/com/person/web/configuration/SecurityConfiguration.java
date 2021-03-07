package com.person.web.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfiguration {

    // A default one:
    private final PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    @Bean
    public UserDetailsService authentication() {
        final UserDetails leo = User.builder()
                                    .username("leo")
                                    .password(passwordEncoder.encode("lein23"))
                                    .roles("USER", "ADMIN")
                .build();

        final UserDetails brenda = User.builder()
                .username("brenda")
                .password(passwordEncoder.encode("brenda23"))
                .roles("USER")
                .build();

        System.out.printf("Leo's password >>> [%s]\n", leo.getPassword());
        System.out.printf("Brenda's password >>> [%s]\n", brenda.getPassword());

        return new InMemoryUserDetailsManager(leo, brenda);
    }

}
