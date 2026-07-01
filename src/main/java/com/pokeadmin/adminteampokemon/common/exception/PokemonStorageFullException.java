package com.pokeadmin.adminteampokemon.common.exception;

public class PokemonStorageFullException extends BusinessException {
    
    public PokemonStorageFullException() {
        super("No space available for this Pokemon");
    }
    
}
