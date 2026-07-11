package com.pokeadmin.adminteampokemon.trainer;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import com.pokeadmin.adminteampokemon.support.BaseIntegrationTest;



class TrainerControllerIntegrationTest extends BaseIntegrationTest {

    @Test
    void shouldReturnUnauthorized() throws Exception {

        mockMvc.perform(get("/trainers"))
                .andExpect(status().isForbidden());

    }

}