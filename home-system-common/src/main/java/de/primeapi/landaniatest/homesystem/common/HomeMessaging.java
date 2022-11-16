package de.primeapi.landaniatest.homesystem.common;

import de.primeapi.landaniatest.homesystem.common.configurations.MessagesConfiguration;

/**
 * @author Lukas S. PrimeAPI
 * created on 16.11.2022
 * crated for Landania-home-system
 */
public class HomeMessaging {

	private static HomeMessaging instance;
	private final MessagesConfiguration configuration;

	public HomeMessaging(MessagesConfiguration configuration) {
		instance = this;
		this.configuration = configuration;
	}

	public static HomeMessaging getInstance() {
		return instance;
	}

	public static String getMessage(String path, String... replacements) {
		return instance.readMessage(path, replacements);
	}

	public static String getMessageWithoutPrefix(String path, String... replacements) {
		return instance.readMessageWithoutPrefix(path, replacements);
	}

	private String readMessage(String path, String... replacements) {
		String message = configuration.getMessages().get(path);
		if (message == null) {
			return "§cMessage not found: " + path;
		}
		for (int i = 0; i < replacements.length; i++) {
			message = message.replace("{" + i + "}", replacements[i]);
		}
		return (configuration.getPrefix() + message).replace("&", "§");
	}

	private String readMessageWithoutPrefix(String path, String... replacements) {
		String message = configuration.getMessages().get(path);
		if (message == null) {
			return "§cMessage not found: " + path;
		}
		for (int i = 0; i < replacements.length; i++) {
			message = message.replace("{" + i + "}", replacements[i]);
		}
		return message.replace("&", "§");
	}


}
