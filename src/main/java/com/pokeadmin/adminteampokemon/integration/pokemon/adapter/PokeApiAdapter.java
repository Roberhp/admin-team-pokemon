package com.pokeadmin.adminteampokemon.integration.pokemon.adapter;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;

import com.pokeadmin.adminteampokemon.common.exception.PokemonNotFoundInPokeApiException;
import com.pokeadmin.adminteampokemon.config.PokeApiProperties;
import com.pokeadmin.adminteampokemon.integration.pokemon.client.PokemonClient;
import com.pokeadmin.adminteampokemon.integration.pokemon.dto.PokeApiPokemonResponse;
import com.pokeadmin.adminteampokemon.integration.pokemon.model.PokemonInfo;

@Component  
public class PokeApiAdapter implements PokemonClient{
    
    private final RestClient restClient;
    private final PokeApiProperties properties;

    public PokeApiAdapter(RestClient restClient,PokeApiProperties properties){
        this.restClient = restClient;
        this.properties = properties;
    }

    @Override
    public PokemonInfo findPokemonByPokedexNumber(Integer numPokedex) {
        try {
            PokeApiPokemonResponse response = restClient.get()
                                                        .uri(properties.getBaseUrl() + "/pokemon/" + numPokedex)
                                                        .retrieve()
                                                        .body(PokeApiPokemonResponse.class);
            if (response == null) {
                throw new PokemonNotFoundInPokeApiException();
            }

            return new PokemonInfo(
                    response.getId(),
                    response.getName(),
                    response.getSprites().getFrontDefault());
        } catch (RestClientResponseException ex) {
            if (ex.getStatusCode().value() == 404) {
                throw new PokemonNotFoundInPokeApiException();
            }
            throw ex;
        }
    }
    
}
