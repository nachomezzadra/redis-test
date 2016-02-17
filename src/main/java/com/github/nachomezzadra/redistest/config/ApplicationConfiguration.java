package com.github.nachomezzadra.redistest.config;

import com.github.nachomezzadra.redistest.domain.CustomPojo;
import com.github.nachomezzadra.redistest.repositories.CustomPojoRepository;
import com.github.nachomezzadra.redistest.repositories.CustomPojoRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@Import(value = RedisConnectionConfiguration.class)
public class ApplicationConfiguration {


    @Bean
    @Autowired
    public RedisTemplate<String, String> redisTemplateString(
            final JedisConnectionFactory connectionFactory) {
        final RedisTemplate<String, String> template =
                new RedisTemplate<String, String>();
        template.setConnectionFactory(connectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new StringRedisSerializer());
        template.setStringSerializer(new StringRedisSerializer());
        return template;
    }


    @Bean
    @Autowired
    public RedisTemplate<String, CustomPojo> redisTemplateCustomPojo(
            final JedisConnectionFactory connectionFactory) {
        final RedisTemplate<String, CustomPojo> template =
                new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        return template;
    }


    @Bean
    @Autowired
    public CustomPojoRepository customPojoRepository(RedisTemplate<String, CustomPojo> redisTemplate) {
        CustomPojoRepositoryImpl repository = new CustomPojoRepositoryImpl();
        repository.setRedisTemplate(redisTemplate);
        return repository;
    }


}
