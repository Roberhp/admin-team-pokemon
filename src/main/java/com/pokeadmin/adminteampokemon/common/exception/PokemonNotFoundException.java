package com.pokeadmin.adminteampokemon.common.exception;

import org.springframework.http.HttpStatus;

public class PokemonNotFoundException extends BusinessException {

    public PokemonNotFoundException() {
        super("Pokemon does not exist",
                HttpStatus.NOT_FOUND);
    }
    
}
