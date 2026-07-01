package com.pokeadmin.adminteampokemon.common.exception;

public class PokemonAlreadyExistsException extends BusinessException {

    public PokemonAlreadyExistsException() {
        super("Pokemon already exists");
    }
    
}
