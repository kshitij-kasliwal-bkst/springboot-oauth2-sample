// Copyright (c) 2016 BankSight Software Systems, Inc. All rights reserved.
package org.sample.google.oauth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * configuration class for redis data store. Default Jedis connection factory {@link JedisConnectionFactory} has been
 * used to take connections to redis store. Redis template is used to perform any CRUD operations on the redis store.
 * Redis template {@link RedisTemplate} configurations related to connection factory, value serializer, key serializer
 * are set.
 * 
 * @author kk
 *
 */
@Configuration
public class RedisConfiguration {

    /**
     * 
     */
    private static final int MIN_EVICTABLE_IDLE_TIME_MILLIS = 60000;
    /**
     * 
     */
    private static final int NUM_TESTS_PER_EVICTION_RUN = -1;
    /**
     * 
     */
    private static final int TIME_BETWEEN_EVICTION_RUNS_MILLIS = 30000;
    /**
     * 
     */
    private static final int SOFT_MIN_EVICTABLE_IDLE_MILLIS = 1800000;
    @Autowired
    private JedisConnectionFactory connectionFactory;

    /**
     * configures template for CRUD operations on redis repository
     * 
     * @return
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        final RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
        connectionFactory.getPoolConfig().setTestOnBorrow(true);
        connectionFactory.getPoolConfig().setMinEvictableIdleTimeMillis(MIN_EVICTABLE_IDLE_TIME_MILLIS);
        connectionFactory.getPoolConfig().setSoftMinEvictableIdleTimeMillis(SOFT_MIN_EVICTABLE_IDLE_MILLIS);
        connectionFactory.getPoolConfig().setTimeBetweenEvictionRunsMillis(TIME_BETWEEN_EVICTION_RUNS_MILLIS);
        connectionFactory.getPoolConfig().setNumTestsPerEvictionRun(NUM_TESTS_PER_EVICTION_RUN);
        connectionFactory.getPoolConfig().setTestWhileIdle(true);

        template.setConnectionFactory(connectionFactory);
        template.setValueSerializer(new Jackson2JsonRedisSerializer<Object>(Object.class));
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(new Jackson2JsonRedisSerializer<Object>(Object.class));
        return template;
    }

}
