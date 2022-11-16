package de.primeapi.landaniatest.homesystem.common;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.primeapi.landaniatest.homesystem.common.configurations.MessagesConfiguration;
import de.primeapi.landaniatest.homesystem.common.configurations.MySQLConfiguration;
import de.primeapi.landaniatest.homesystem.common.io.ConfigurationUtils;
import lombok.Getter;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Logger;

/**
 * @author Lukas S. PrimeAPI
 * created on 14.11.2022
 * crated for Landania-home-system
 */
@Getter
public class JsonManager {


	static private JsonManager instance;
	private final Gson gson;
	final private Logger logger;
	final private HomeMessaging messaging;
	MySQLConfiguration mySQLConfiguration;
	MessagesConfiguration messagesConfiguration;

	public JsonManager(Logger logger) {
		instance = this;
		this.logger = logger;
		gson = new GsonBuilder().setPrettyPrinting().create();
		initializeConfigs();
		messaging = new HomeMessaging(messagesConfiguration);
	}

	public static JsonManager getInstance() {
		return instance;
	}

	private void initializeConfigs() {
		try {
			getLogger().info("Initializing MySQL-Configuration");
			mySQLConfiguration = ConfigurationUtils.initializeConfiguration("mysql", MySQLConfiguration.class);
			getLogger().info("MySQL-Configuration successfully initialized");
		} catch (IOException | InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException e) {
			getLogger().warning("MySQL-Configuration could not be initialized: ");
			e.printStackTrace();
		}
		try {
			getLogger().info("Initializing Messages-Configuration");
			messagesConfiguration = ConfigurationUtils.initializeConfiguration("messages",
			                                                                   MessagesConfiguration.class);
			getLogger().info("Messages-Configuration successfully initialized");
		} catch (IOException | InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException e) {
			getLogger().warning("Messages-Configuration could not be initialized: ");
			e.printStackTrace();
		}
	}


}
