package com.pokeadmin.adminteampokemon.support;

import java.time.LocalDateTime;

import com.pokeadmin.adminteampokemon.capture.entity.CapturedPokemonEntity;
import com.pokeadmin.adminteampokemon.capture.entity.PokemonLocation;

public final class PokemonFactory {

    private PokemonFactory() {
    }

    public static CapturedPokemonEntity pikachu() {

        CapturedPokemonEntity pokemon = new CapturedPokemonEntity();

        pokemon.setCapturedPokemonId(TestConstants.PIKACHU_ID);
        pokemon.setTrainer(TrainerFactory.ash());
        pokemon.setNickname("Pikachu");
        pokemon.setPokedexNumber(25);
        pokemon.setLocation(PokemonLocation.TEAM);
        pokemon.setCapturedAt(LocalDateTime.now());
        pokemon.setSpriteUrl("https://img/25.png");

        return pokemon;
    }

    public static CapturedPokemonEntity bulbasaur() {

        CapturedPokemonEntity pokemon = new CapturedPokemonEntity();

        pokemon.setCapturedPokemonId(TestConstants.BULBASAUR_ID);
        pokemon.setTrainer(TrainerFactory.ash());
        pokemon.setNickname("Bulbasaur");
        pokemon.setPokedexNumber(1);
        pokemon.setLocation(PokemonLocation.TEAM);
        pokemon.setCapturedAt(LocalDateTime.now());
        pokemon.setSpriteUrl("https://img/1.png");

        return pokemon;
    }

    public static CapturedPokemonEntity charizard() {

        CapturedPokemonEntity pokemon = new CapturedPokemonEntity();

        pokemon.setCapturedPokemonId(TestConstants.CHARIZARD_ID);
        pokemon.setTrainer(TrainerFactory.ash());
        pokemon.setNickname("Charizard");
        pokemon.setPokedexNumber(6);
        pokemon.setLocation(PokemonLocation.STORAGE);
        pokemon.setCapturedAt(LocalDateTime.now());
        pokemon.setSpriteUrl("https://img/6.png");

        return pokemon;
    }

}