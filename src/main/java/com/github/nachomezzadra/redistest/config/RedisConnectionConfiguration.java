package com.github.nachomezzadra.redistest.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;

@Configuration
@PropertySource(value = {"classpath:redistest.properties"})
public class RedisConnectionConfiguration {

    @Value("${redis.port}")
    private String redisPort;

    @Value("${redis.host}")
    private String redisHost;


    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public JedisPoolConfig poolConfig() {
        final JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setTestOnBorrow(true);
        jedisPoolConfig.setMaxTotal(10);
        return jedisPoolConfig;
    }

    @Bean
    public JedisConnectionFactory connectionFactory() {
        final JedisConnectionFactory connectionFactory =
                new JedisConnectionFactory(poolConfig());
        connectionFactory.setHostName(this.redisHost);
        connectionFactory.setDatabase(Protocol.DEFAULT_DATABASE);
        connectionFactory.setPort(Integer.valueOf(this.redisPort));
        return connectionFactory;
    }
}
