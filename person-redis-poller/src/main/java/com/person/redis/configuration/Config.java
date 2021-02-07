package com.person.redis.configuration;

import com.person.redis.domain.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class Config {

    @Bean
    public RedisOperations<String, Person> redisOperations(final RedisConnectionFactory factory) {
        Jackson2JsonRedisSerializer<Person> serializer =
                new Jackson2JsonRedisSerializer<>(Person.class);

        RedisTemplate<String, Person> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        template.setDefaultSerializer(serializer);
        template.setKeySerializer(new StringRedisSerializer());

        return template;
    }

}
