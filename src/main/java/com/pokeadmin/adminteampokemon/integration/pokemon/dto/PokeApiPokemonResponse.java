package com.pokeadmin.adminteampokemon.integration.pokemon.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PokeApiPokemonResponse {
    
    private Integer id;
    private String name;
    private PokeApiSpritesResponse sprites;
}
