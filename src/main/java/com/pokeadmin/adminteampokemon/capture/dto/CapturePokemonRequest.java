package com.pokeadmin.adminteampokemon.capture.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CapturePokemonRequest {

    private Integer pokedexNumber;
    private String nickname;
}
