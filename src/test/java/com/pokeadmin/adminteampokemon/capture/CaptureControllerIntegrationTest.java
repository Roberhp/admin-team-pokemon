package com.pokeadmin.adminteampokemon.capture;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import com.pokeadmin.adminteampokemon.support.BaseIntegrationTest;



class CaptureControllerIntegrationTest extends BaseIntegrationTest {

    @Test
    void captureRequiresAuthentication() throws Exception {

        mockMvc.perform(
                post("/captures")
                        .contentType(JSON)
                        .content("""
                                {
                                  "pokemonId":25
                                }
                                """)
        ).andExpect(status().isForbidden());

    }

}