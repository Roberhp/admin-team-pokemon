package com.pokeadmin.adminteampokemon;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.pokeadmin.adminteampokemon.capture.repository.CapturePokemonRepository;
import com.pokeadmin.adminteampokemon.trainer.repository.TrainerRepository;

@SpringBootTest(properties = {
		"spring.autoconfigure.exclude=org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration,"
				+ "org.springframework.boot.hibernate.autoconfigure.HibernateJpaAutoConfiguration",
		"jwt.secret=test-secret-key-for-admin-team-pokemon-tests",
		"jwt.expiration=3600000"
})
class AdminTeamPokemonApplicationTests {

	@MockitoBean
	private TrainerRepository trainerRepository;

	@MockitoBean
	private CapturePokemonRepository capturePokemonRepository;

	@Test
	void contextLoads() {
	}

}
