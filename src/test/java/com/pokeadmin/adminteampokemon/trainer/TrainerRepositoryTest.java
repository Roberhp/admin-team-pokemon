package com.pokeadmin.adminteampokemon.trainer;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.pokeadmin.adminteampokemon.support.BaseIntegrationTest;
import com.pokeadmin.adminteampokemon.support.TestData;
import com.pokeadmin.adminteampokemon.trainer.repository.TrainerRepository;
import com.pokeadmin.adminteampokemon.trainer.entity.TrainerEntity;

class TrainerRepositoryTest extends BaseIntegrationTest {

    @Autowired
    private TrainerRepository repository;

    @Test
    void shouldSaveTrainer() {

        var trainer = TestData.ash();
        trainer.setTrainerId(null);

        TrainerEntity saved = repository.save(trainer);

        assertThat(saved.getTrainerId()).isNotNull();

    }

}
