package com.pokeadmin.adminteampokemon.pokemon;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import com.pokeadmin.adminteampokemon.support.BaseIntegrationTest;


class PokemonCacheIntegrationTest extends BaseIntegrationTest {

    @Autowired
    private StringRedisTemplate redis;

    @Test
    void shouldStoreValueInRedis() {

        redis.opsForValue().set(
                "pokemon:25",
                "pikachu",
                Duration.ofMinutes(5));

        String value = redis.opsForValue().get("pokemon:25");

        assertEquals("pikachu", value);

    }

}