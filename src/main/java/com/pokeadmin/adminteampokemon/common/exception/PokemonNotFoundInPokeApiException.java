package com.pokeadmin.adminteampokemon.common.exception;

import org.springframework.http.HttpStatus;

public class PokemonNotFoundInPokeApiException extends BusinessException {

    public PokemonNotFoundInPokeApiException() {
        super("Pokemon does not exist",
                HttpStatus.NOT_FOUND);
    }
    
}
