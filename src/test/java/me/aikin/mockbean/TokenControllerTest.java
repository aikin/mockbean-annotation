package me.aikin.mockbean;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

public class TokenControllerTest extends ApiBaseTest {

    @MockBean(name = "defaultRedisTemplate")
    private RedisTemplate redisTemplate;

    private ValueOperations mockedValueOperations = Mockito.mock(ValueOperations.class);;

    @BeforeEach
    public void setup() {
        super.setup();
        when(redisTemplate.opsForValue()).thenReturn(mockedValueOperations);
    }

    @Test
    void should_can_get_cached_token_when_redis_has_cached() {
        when(mockedValueOperations.get(anyString())).thenReturn("test redis token");
        given()
        .when().
                get("/api/token").
        then().
                body(equalTo("cached test redis token"));

    }

    @Test
    void should_can_fresh_token_with_cached_when_redis_has_not_cached() {
        when(mockedValueOperations.get(anyString())).thenReturn(null);
        given().
        when().
                get("/api/token").
        then().
                body(equalTo("fresh token"));

    }
}
