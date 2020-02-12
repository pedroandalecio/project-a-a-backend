package br.com.acai.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.acai.services.db.DBService;

@Configuration
public class DevConfig {

	@Autowired
	private DBService dbService;

	@Bean
	public boolean instantiateDatabase() {

		dbService.instantiateTestDatabase();

		return true;
	}

}
