package me.aikin.mockbean;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.core.RedisTemplate;

@Profile("test")
@Configuration
public class RedisTemplateBeanConfigurationMocker {
    @Bean(name = "defaultRedisTemplate")
    public RedisTemplate redisTemplate() {
        return Mockito.mock(RedisTemplate.class);
    }
}
