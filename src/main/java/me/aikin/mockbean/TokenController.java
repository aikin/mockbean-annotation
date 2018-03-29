package me.aikin.mockbean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/token")
public class TokenController {
    @Autowired
    @Qualifier("defaultRedisTemplate")
    private RedisTemplate redisTemplate;

    @GetMapping
    public String getToken() {
        String redisToken = (String) redisTemplate.opsForValue().get("redis-token");
        if (StringUtils.hasText(redisToken)) {
            return "cached " + redisToken;
        }
        return "fresh token";
    }
}
