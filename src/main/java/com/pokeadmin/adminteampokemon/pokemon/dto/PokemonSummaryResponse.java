package com.pokeadmin.adminteampokemon.pokemon.dto;


import lombok.Data;

@Data
public class PokemonSummaryResponse {
    
    private Long totalTeam;
    private Long totalStorage;
    private Long total;

    public PokemonSummaryResponse(Long totalTeam, Long totalStorage) {
        this.totalTeam = totalTeam;
        this.totalStorage = totalStorage;
        this.total = totalStorage + totalTeam;
    }
}
