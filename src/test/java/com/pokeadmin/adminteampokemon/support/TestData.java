package com.pokeadmin.adminteampokemon.support;

import com.pokeadmin.adminteampokemon.capture.entity.CapturedPokemonEntity;
import com.pokeadmin.adminteampokemon.integration.pokemon.model.PokemonInfo;
import com.pokeadmin.adminteampokemon.trainer.entity.TrainerEntity;

public final class TestData {

    private TestData() {
    }

    public static TrainerEntity ash() {
        return TrainerFactory.ash();
    }

    public static TrainerEntity misty() {
        return TrainerFactory.misty();
    }

    public static CapturedPokemonEntity pikachu() {
        return PokemonFactory.pikachu();
    }

    public static CapturedPokemonEntity bulbasaur() {
        return PokemonFactory.bulbasaur();
    }

    public static PokemonInfo pokemonInfo() {

        return new PokemonInfo(
                25,
                "pikachu",
                "https://img/25.png");
    }

}