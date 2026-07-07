package com.pokeadmin.adminteampokemon.capture;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.pokeadmin.adminteampokemon.capture.entity.CapturedPokemonEntity;
import com.pokeadmin.adminteampokemon.capture.repository.CapturePokemonRepository;
import com.pokeadmin.adminteampokemon.support.BaseIntegrationTest;
import com.pokeadmin.adminteampokemon.support.TestData;
import com.pokeadmin.adminteampokemon.trainer.entity.TrainerEntity;
import com.pokeadmin.adminteampokemon.trainer.repository.TrainerRepository;

class CaptureRepositoryTest extends BaseIntegrationTest {

    @Autowired
    private CapturePokemonRepository repository;

    @Autowired
    private TrainerRepository trainerRepository;

    @BeforeEach
    void cleanDatabase() {
        repository.deleteAll();
        trainerRepository.deleteAll();
    }

    @Test
    void shouldSavePokemon() {
        TrainerEntity trainer = savedTrainer();
        CapturedPokemonEntity pokemon = newPokemon(TestData.pikachu(), trainer);

        repository.save(pokemon);

        assertEquals(1, repository.count());
    }

    @Test
    void shouldFindTrainerPokemons() {
        TrainerEntity trainer = savedTrainer();

        repository.save(newPokemon(TestData.pikachu(), trainer));
        repository.save(newPokemon(TestData.bulbasaur(), trainer));

        List<CapturedPokemonEntity> result =
                repository.findAll();

        assertEquals(2, result.size());

    }

    private TrainerEntity savedTrainer() {
        TrainerEntity trainer = TestData.ash();
        trainer.setTrainerId(null);

        return trainerRepository.save(trainer);
    }

    private CapturedPokemonEntity newPokemon(CapturedPokemonEntity pokemon, TrainerEntity trainer) {
        pokemon.setCapturedPokemonId(null);
        pokemon.setTrainer(trainer);

        return pokemon;
    }

}
