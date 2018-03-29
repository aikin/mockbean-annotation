package me.aikin.mockbean;


import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.JedisPoolConfig;

import java.net.UnknownHostException;

@Profile("!test")
@Configuration
@EnableConfigurationProperties({ RedisProperties.class })
public class RedisConfiguration {

    @Bean(name = "defaultRedisTemplate")
    public RedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
        RedisTemplate template = new RedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }

    @Bean
    public RedisConnectionFactory redisConnectionFactory(RedisProperties properties) {
        return setupRedisConnection(properties);
    }

    private RedisConnectionFactory setupRedisConnection(RedisProperties properties) {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        RedisProperties.Pool props = properties.getPool();
        poolConfig.setMaxTotal(props.getMaxActive());
        poolConfig.setMaxIdle(props.getMaxIdle());
        poolConfig.setMinIdle(props.getMinIdle());
        poolConfig.setMaxWaitMillis(props.getMaxWait());

        JedisConnectionFactory factory = new JedisConnectionFactory(poolConfig);
        factory.setHostName(properties.getHost());
        factory.setPort(properties.getPort());
        if (properties.getPassword() != null) {
            factory.setPassword(properties.getPassword());
        }
        factory.setUseSsl(false);
        factory.setDatabase(properties.getDatabase());

        return factory;
    }
}
