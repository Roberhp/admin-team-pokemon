package com.pokeadmin.adminteampokemon.auth.dto;


import lombok.Getter;

@Getter
public class RegisterTrainerRequest {
    
    private String username;
    private String password;
    private String trainerName;
    private String originTown;
}
