package com.pokeadmin.adminteampokemon.auth;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import com.pokeadmin.adminteampokemon.support.BaseIntegrationTest;



class AuthIntegrationTest extends BaseIntegrationTest {

    @Test
    void loginShouldReturnUnauthorized() throws Exception {

        mockMvc.perform(
                post("/auth/login")
                        .contentType(JSON)
                        .content("""
                                {
                                  "username":"ash",
                                  "password":"1234"
                                }
                                """)
        ).andExpect(status().isUnauthorized());

    }

}