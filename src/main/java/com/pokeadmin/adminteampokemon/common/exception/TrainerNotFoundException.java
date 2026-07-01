package com.pokeadmin.adminteampokemon.common.exception;

public class TrainerNotFoundException extends BusinessException {

    public TrainerNotFoundException() {
        super("Trainer not found");
    }
    
}
