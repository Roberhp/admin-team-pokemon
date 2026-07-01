package com.pokeadmin.adminteampokemon.integration.pokemon.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PokemonInfo {

    private Integer numPokedex;

    private String nombre;
    private String imageUrl;

}
