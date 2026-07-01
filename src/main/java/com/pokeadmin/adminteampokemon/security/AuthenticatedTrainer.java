package com.pokeadmin.adminteampokemon.security;

public class AuthenticatedTrainer {

    private final Long id;
    private final String username;

    public AuthenticatedTrainer(
            Long id,
            String username) {

        this.id = id;
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }
}