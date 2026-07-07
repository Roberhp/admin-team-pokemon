package com.pokeadmin.adminteampokemon.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.pokeadmin.adminteampokemon.capture.entity.CapturedPokemonEntity;
import com.pokeadmin.adminteampokemon.capture.entity.PokemonLocation;
import com.pokeadmin.adminteampokemon.capture.repository.CapturePokemonRepository;
import com.pokeadmin.adminteampokemon.integration.pokemon.client.PokemonClient;
import com.pokeadmin.adminteampokemon.integration.pokemon.model.PokemonInfo;
import com.pokeadmin.adminteampokemon.pokemon.dto.PokedexSearchInfoResponse;
import com.pokeadmin.adminteampokemon.pokemon.dto.PokemonInfoResponse;
import com.pokeadmin.adminteampokemon.pokemon.dto.PokemonSummaryResponse;
import com.pokeadmin.adminteampokemon.pokemon.service.PokemonQueryService;
import com.pokeadmin.adminteampokemon.support.TestData;

@ExtendWith(MockitoExtension.class)
class PokemonServiceTest {

    @Mock
    private CapturePokemonRepository capturePokemonRepository;

    @Mock
    private PokemonClient pokemonClient;

    @InjectMocks
    private PokemonQueryService service;

    @Test
    void shouldReturnPokemonInfoResponsePage() {
        Pageable pageable = PageRequest.of(0, 10);

        Page<CapturedPokemonEntity> repositoryPage = new PageImpl<>(
            List.of(
                TestData.pikachu(),
                TestData.bulbasaur()
            )
        );

        when(capturePokemonRepository.findByTrainerTrainerIdAndLocation(
            1L,
            PokemonLocation.TEAM,
            pageable
        )).thenReturn(repositoryPage);

        Page<PokemonInfoResponse> result = service.findPokemonByLocation(
            1L,
            PokemonLocation.TEAM,
            pageable
        );

        assertThat(result.getContent()).hasSize(2);
        assertThat(result.getContent().get(0).getNickname()).isEqualTo("Pikachu");
        assertThat(result.getContent().get(1).getNickname()).isEqualTo("Bulbasaur");

        verify(capturePokemonRepository).findByTrainerTrainerIdAndLocation(
            1L,
            PokemonLocation.TEAM,
            pageable
        );
    }

    @Test
    void shouldReturnEmptyPage() {
        Pageable pageable = PageRequest.of(0, 10);

        when(capturePokemonRepository.findByTrainerTrainerIdAndLocation(
            1L,
            PokemonLocation.TEAM,
            pageable
        )).thenReturn(new PageImpl<>(Collections.emptyList()));

        Page<PokemonInfoResponse> result = service.findPokemonByLocation(
            1L,
            PokemonLocation.TEAM,
            pageable
        );

        assertThat(result).isEmpty();
    }

    @Test
    void shouldReturnPokemonSummary() {
        when(capturePokemonRepository.countByTrainerTrainerIdAndLocation(
            1L,
            PokemonLocation.TEAM
        )).thenReturn(3L);

        when(capturePokemonRepository.countByTrainerTrainerIdAndLocation(
            1L,
            PokemonLocation.STORAGE
        )).thenReturn(6L);

        PokemonSummaryResponse summary = service.getTrainerPokemonSummary(1L);

        assertThat(summary.getTotalTeam()).isEqualTo(3);
        assertThat(summary.getTotalStorage()).isEqualTo(6);

        verify(capturePokemonRepository).countByTrainerTrainerIdAndLocation(
            1L,
            PokemonLocation.TEAM
        );

        verify(capturePokemonRepository).countByTrainerTrainerIdAndLocation(
            1L,
            PokemonLocation.STORAGE
        );
    }

    @Test
    void shouldReturnPokemonInformationFromPokeApi() {
        PokemonInfo pokemonInfo = TestData.pokemonInfo();

        when(pokemonClient.findPokemonByPokedexNumber(25)).thenReturn(pokemonInfo);

        PokedexSearchInfoResponse response = service.getInfoPokemon(25);

        assertThat(response).isNotNull();
        assertThat(response.getNumPokedex()).isEqualTo(25);
        assertThat(response.getNombre()).isEqualTo("pikachu");
        assertThat(response.getImageUrl()).isEqualTo("https://img/25.png");

        verify(pokemonClient).findPokemonByPokedexNumber(25);
    }
}
