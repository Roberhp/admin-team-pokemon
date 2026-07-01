package com.pokeadmin.adminteampokemon.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@ConfigurationProperties(prefix = "pokeapi")
@Component
@Data
public class PokeApiProperties {

    private String baseUrl;

}
