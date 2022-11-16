package de.primeapi.landaniatest.homesystem.api.sql.handler;

import de.primeapi.landaniatest.homesystem.api.caching.Home;
import de.primeapi.landaniatest.homesystem.api.caching.HomesCache;
import de.primeapi.landaniatest.homesystem.api.sql.Database;
import de.primeapi.landaniatest.homesystem.api.sql.utils.Retriever;
import lombok.AllArgsConstructor;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author Lukas S. PrimeAPI
 * created on 15.11.2022
 * crated for Landania-home-system
 */
@AllArgsConstructor
public class HomeProvider {
	private Database database;
	private HomesCache homesCache;

//	public Retriever<Home> getHomeByUUIDAndName(UUID uuid, String name) {
//		return database.select("SELECT * FROM player_homes WHERE `uuid` = ? AND `name` = ?")
//		               .parameters(uuid.toString(), name)
//		               .execute(Object.class)
//		               .getRowHashMap().map(Home::fromDatabase);
//	}

	public Retriever<Set<Home>> getHomesByUUID(UUID uuid) {
		return database.select("SELECT * FROM player_homes WHERE `uuid` = ?")
		               .parameters(uuid.toString())
		               .execute(Object.class)
		               .getAllRowHashMap().map(set ->
				                                       set.stream().map(Home::fromDatabase)
				                                          .collect(Collectors.toSet())
		                                      );
	}


}
