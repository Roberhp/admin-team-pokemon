package com.pokeadmin.adminteampokemon.integration.pokemon.client;

import com.pokeadmin.adminteampokemon.integration.pokemon.model.PokemonInfo;

public interface PokemonClient {

    PokemonInfo findPokemonByPokedexNumber(Integer numPokedex);

}
