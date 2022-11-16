package de.primeapi.landaniatest.homesystem.api;

import de.primeapi.landaniatest.homesystem.api.caching.Home;
import de.primeapi.landaniatest.homesystem.api.caching.HomesCache;
import de.primeapi.landaniatest.homesystem.api.exceptions.NotLoadedException;
import de.primeapi.landaniatest.homesystem.api.sql.Database;
import de.primeapi.landaniatest.homesystem.api.sql.handler.HomeConsumer;
import de.primeapi.landaniatest.homesystem.api.sql.handler.HomeProvider;
import de.primeapi.landaniatest.homesystem.common.configurations.MySQLConfiguration;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

/**
 * @author Lukas S. PrimeAPI
 * created on 15.11.2022
 * crated for Landania-home-system
 */
public class HomeAPI {


	private final Logger logger;
	private final HomesCache homesCache;
	private final HomeConsumer homeConsumer;
	private final HomeProvider homeProvider;
	private Database database;

	public HomeAPI(MySQLConfiguration configuration, Logger logger) {
		this.logger = logger;
		logger.info("Initializing MySQL-Connection");
		try {
			database = new Database(
					configuration.getHost(), configuration.getDatabase(), configuration.getUsername(),
					configuration.getPassword()
			);
			createTables();
			logger.info("MySQL-Connection successfully initialized");
		} catch (SQLException e) {
			logger.warning("MySQL-Connection could NOT be initialized: " + e.getMessage());
		}

		homesCache = new HomesCache();
		homeConsumer = new HomeConsumer(database, homesCache);
		homeProvider = new HomeProvider(database, homesCache);
	}

	private void createTables() {
		database.update("CREATE TABLE IF NOT EXISTS player_homes (" +
				                "`id` INT NOT NULL AUTO_INCREMENT," +
				                "`uuid` VARCHAR(36) NOT NULL," +
				                "`name` VARCHAR(10) NOT NULL," +
				                "`pos_x` DOUBLE NOT NULL," +
				                "`pos_y` DOUBLE NOT NULL," +
				                "`pos_z` DOUBLE NOT NULL," +
				                "`pos_yaw` FLOAT NOT NULL," +
				                "`pos_pitch` FLOAT NOT NULL," +
				                "`pos_world` VARCHAR(64) NOT NULL," +
				                "PRIMARY KEY (`id`));")
		        .execute();
	}

	public Home getHome(Player player, String name) throws NotLoadedException {
		Set<Home> homes = homesCache.getHomes(player.getUniqueId());
		if (homes == null) {
			throw new NotLoadedException("Homes from player " + player.getName() + " has not been loaded yet!");
		}
		return homes.stream().filter(home -> home.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
	}

	public Set<Home> getHomes(Player player) throws NotLoadedException {
		Set<Home> homes = homesCache.getHomes(player.getUniqueId());
		if (homes == null) {
			throw new NotLoadedException("Homes from player " + player.getName() + " has not been loaded yet!");
		}
		return homes;
	}

	public void addHome(Player player, String name, Location location) {
		Home home = new Home(-1, player.getUniqueId(), name, location);
		homeConsumer.saveHome(home).submit(homesCache::addHome);
	}

	public void removeHome(Player player, Home home) throws NotLoadedException {
		homesCache.removeHome(home);
		homeConsumer.deleteHome(home);
	}

	public void deleteAllHomes(Player player) {
		homesCache.removePlayer(player.getUniqueId());
		homesCache.addHomes(player.getUniqueId(), new HashSet<>());
		homeConsumer.deleteAllHomes(player.getUniqueId());
	}

	/**
	 * This loads the Players Homes from the Database.
	 * Should be called when the Player joins the Server.
	 */
	public void loadHomes(Player player) {
		homeProvider.getHomesByUUID(player.getUniqueId())
		            .submit(homes -> homesCache.addHomes(player.getUniqueId(), homes));
	}

	/**
	 * This saves the Players Homes to the Database.
	 * Should be called when the Player leaves the Server.
	 */
	public void unloadHomes(Player player) {
		homesCache.removePlayer(player.getUniqueId());
	}

}
