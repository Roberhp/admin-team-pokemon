package com.pokeadmin.adminteampokemon.pokemon.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MovePokemonResponse {
    private Long capturedPokemonId;
    private String location;
}
