package de.primeapi.landaniatest.homesystem.common.oop;

import de.primeapi.landaniatest.homesystem.common.JsonManager;
import de.primeapi.landaniatest.homesystem.common.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * @author Lukas S. PrimeAPI
 * created on 14.11.2022
 * crated for Landania-home-system
 */
public abstract class Configuration {


	/**
	 * Saves the configuration to a specified file. Checks if the file exists and creates it if it doesn't.
	 *
	 * @param name The name of the file without filetype-ending
	 */
	public void save(String name) throws IOException {
		File file = FileUtils.createFile(name);
		String jsonString = JsonManager.getInstance().getGson().toJson(this);
		FileUtils.writeToFile(file, jsonString);
	}

	public abstract void insertDefaultValues();


}
