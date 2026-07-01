package com.pokeadmin.adminteampokemon.common.exception;

public class TrainerAlreadyExistsException extends BusinessException {

    public TrainerAlreadyExistsException(String username) {
        super("Username already exists");
    }
}