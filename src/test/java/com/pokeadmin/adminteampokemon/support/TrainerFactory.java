package com.pokeadmin.adminteampokemon.support;

import com.pokeadmin.adminteampokemon.trainer.entity.TrainerEntity;

public final class TrainerFactory {

    private TrainerFactory() {
    }

    public static TrainerEntity ash() {

        TrainerEntity trainer = new TrainerEntity();

        trainer.setTrainerId(TestConstants.TRAINER_ASH);
        trainer.setUsername("ash");
        trainer.setPassword("password");
        trainer.setTrainerName("Ash");
        trainer.setRegion("Kanto");

        return trainer;
    }

    public static TrainerEntity misty() {

        TrainerEntity trainer = new TrainerEntity();

        trainer.setTrainerId(TestConstants.TRAINER_MISTY);
        trainer.setUsername("misty");
        trainer.setPassword("password");
        trainer.setTrainerName("Misty");
        trainer.setRegion("Kanto");

        return trainer;
    }

}
