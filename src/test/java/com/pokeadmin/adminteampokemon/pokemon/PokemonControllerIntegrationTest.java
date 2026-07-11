package com.pokeadmin.adminteampokemon.pokemon;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import com.pokeadmin.adminteampokemon.support.BaseIntegrationTest;


class PokemonControllerIntegrationTest extends BaseIntegrationTest {

    @Test
    void shouldRejectAnonymousUser() throws Exception {

        mockMvc.perform(get("/pokemon/25")
        ).andExpect(status().isForbidden());

    }

}