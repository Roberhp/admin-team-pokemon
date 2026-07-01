package com.pokeadmin.adminteampokemon.pokemon.dto;

import com.pokeadmin.adminteampokemon.capture.entity.PokemonLocation;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MovePokemonRequest {
    
    private PokemonLocation location;
    
}
