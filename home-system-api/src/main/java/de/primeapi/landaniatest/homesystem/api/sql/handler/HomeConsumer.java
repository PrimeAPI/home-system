package de.primeapi.landaniatest.homesystem.api.sql.handler;

import de.primeapi.landaniatest.homesystem.api.caching.Home;
import de.primeapi.landaniatest.homesystem.api.caching.HomesCache;
import de.primeapi.landaniatest.homesystem.api.sql.Database;
import de.primeapi.landaniatest.homesystem.api.sql.utils.Retriever;
import lombok.AllArgsConstructor;

import java.util.UUID;

/**
 * @author Lukas S. PrimeAPI
 * created on 15.11.2022
 * crated for Landania-home-system
 */
@AllArgsConstructor
public class HomeConsumer {
	private Database database;
	private HomesCache homesCache;

	public Retriever<Home> saveHome(Home home) {
		return database.update("INSERT INTO player_homes VALUES (id, ?,?,?,?,?,?,?,?)")
		               .parameters(
				               home.getOwner().toString(), home.getName(), home.getPosition().getX(),
				               home.getPosition().getY(), home.getPosition().getZ(), home.getPosition().getYaw(),
				               home.getPosition().getPitch(), home.getPosition().getWorld().getName()
		                          ).
		               returnGeneratedKeys(Integer.class).get().map(integer -> {
					home.setId(integer);
					return home;
				});
	}

	public void deleteHome(Home home) {
		database.update("DELETE FROM player_homes WHERE id = ?")
		        .parameters(home.getId())
		        .execute();
	}

	public void deleteAllHomes(UUID uuid) {
		database.update("DELETE FROM player_homes WHERE uuid=?")
		        .parameters(uuid.toString())
		        .execute();
	}


}
