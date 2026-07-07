package com.pokeadmin.adminteampokemon.support;

import com.pokeadmin.adminteampokemon.capture.entity.CapturedPokemonEntity;

public final class CaptureFactory {

    private CaptureFactory() {
    }

    public static CapturedPokemonEntity teamPokemon() {
        return PokemonFactory.pikachu();
    }

    public static CapturedPokemonEntity boxedPokemon() {
        return PokemonFactory.charizard();
    }

}