package com.sbur.service.configuration;

import com.sbur.service.domain.Aircraft;
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
    public RedisOperations<String, Aircraft> redisOperations(final RedisConnectionFactory factory) {
        final Jackson2JsonRedisSerializer<Aircraft> serializer =
                new Jackson2JsonRedisSerializer<>(Aircraft.class);

        final RedisTemplate<String, Aircraft> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        template.setDefaultSerializer(serializer);
        template.setKeySerializer(new StringRedisSerializer());

        return template;
    }

}
