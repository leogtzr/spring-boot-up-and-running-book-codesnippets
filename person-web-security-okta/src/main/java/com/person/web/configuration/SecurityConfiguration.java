package com.person.web.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.reactive.function.client.WebClient;

// EnableWebSecurity has @Configuration already.
// @EnableWebSecurity
//public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//
//    // A default one:
//    private final PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//
//    @Bean
//    public UserDetailsService authentication() {
//        final UserDetails leo = User.builder()
//                                    .username("leo")
//                                    .password(passwordEncoder.encode("lein23"))
//                                    .roles("USER", "ADMIN")
//                .build();
//
//        final UserDetails brenda = User.builder()
//                .username("brenda")
//                .password(passwordEncoder.encode("brenda23"))
//                .roles("USER")
//                .build();
//
//        System.out.printf("Leo's password >>> [%s]\n", leo.getPassword());
//        System.out.printf("Brenda's password >>> [%s]\n", brenda.getPassword());
//
//        return new InMemoryUserDetailsManager(leo, brenda);
//    }
//
//    @Override
//    protected void configure(final HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .mvcMatchers("/api/personsadmin/**").hasRole("ADMIN")
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .and()
//                .httpBasic()
//                ;
//    }
//}

// @Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public WebClient client(
            final ClientRegistrationRepository regRepo,
            final OAuth2AuthorizedClientRepository cliRepo) {
        final ServletOAuth2AuthorizedClientExchangeFilterFunction filter =
                new ServletOAuth2AuthorizedClientExchangeFilterFunction(regRepo, cliRepo);

        filter.setDefaultOAuth2AuthorizedClient(true);

        return WebClient.builder()
                .baseUrl("http://localhost:8081/")
                .apply(filter.oauth2Configuration())
                .build();
    }

}
