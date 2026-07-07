package com.pokeadmin.adminteampokemon.pokemon;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.pokeadmin.adminteampokemon.capture.repository.CapturePokemonRepository;
import com.pokeadmin.adminteampokemon.support.BaseIntegrationTest;
import com.pokeadmin.adminteampokemon.support.TestData;
import com.pokeadmin.adminteampokemon.trainer.repository.TrainerRepository;

class PokemonRepositoryTest extends BaseIntegrationTest {

    @Autowired
    private CapturePokemonRepository repository;

    @Autowired
    private TrainerRepository trainerRepository;

    @Test
    void shouldSavePokemon() {

        var pokemon = TestData.pikachu();
        var trainer = TestData.ash();

        trainer.setTrainerId(null);
        pokemon.setCapturedPokemonId(null);
        pokemon.setTrainer(trainerRepository.save(trainer));

        var saved = repository.save(pokemon);

        assertThat(saved.getCapturedPokemonId()).isNotNull();

    }

}
