package com.pokeadmin.adminteampokemon.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

import java.util.List;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class OpenApiConfiguration {

    @Bean
    public OpenAPI adminTeamPokemonOpenAPI() {

        return new OpenAPI()
            .info(
                new Info()
                    .title("Admin Team Pokémon API")
                    .description("REST API for managing Pokémon teams, storage, and trainer authentication.")
                    .version("1.0.0")
                    .contact( new Contact()
                                    .name("Roberto Huerta")
                                    .email("roberto@example.com")
                    )
            )
            .components(
                new Components()
                    .addSecuritySchemes(
                        "bearerAuth",
                        new SecurityScheme()
                            .name("bearerAuth")
                            .type(SecurityScheme.Type.HTTP)
                            .scheme("bearer")
                            .bearerFormat("JWT")
                    )
            )
            .security(
                List.of(
                    new SecurityRequirement().addList("bearerAuth")
                )
            );
    }
}