package com.pokeadmin.adminteampokemon.integration.pokemon.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class PokeApiSpritesResponse {
    
    @JsonProperty("front_default")
    private String frontDefault;

}
