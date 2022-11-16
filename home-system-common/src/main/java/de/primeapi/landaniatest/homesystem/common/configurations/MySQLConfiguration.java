package de.primeapi.landaniatest.homesystem.common.configurations;

import de.primeapi.landaniatest.homesystem.common.oop.Configuration;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Lukas S. PrimeAPI
 * created on 14.11.2022
 * crated for Landania-home-system
 */

@Getter
@Setter
public class MySQLConfiguration extends Configuration {
	private String host;
	private String database;
	private String username;
	private String password;

	@Override
	public void insertDefaultValues() {
		setHost("localhost:3306");
		setDatabase("homesystem");
		setUsername("root");
		setPassword("password");
	}
}
