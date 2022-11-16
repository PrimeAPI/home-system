package de.primeapi.landaniatest.homesystem.common.io;

import de.primeapi.landaniatest.homesystem.common.JsonManager;
import de.primeapi.landaniatest.homesystem.common.oop.Configuration;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author Lukas S. PrimeAPI
 * created on 15.11.2022
 * crated for Landania-home-system
 */
public class ConfigurationUtils {

	public static <T extends Configuration> T initializeConfiguration(String name, Class<T> clazz) throws IOException,
			NoSuchMethodException,
			InvocationTargetException, InstantiationException, IllegalAccessException {
		if (!FileUtils.isFileExisting(name)) {
			Constructor<T> constructor = clazz.getConstructor();
			T t = constructor.newInstance();
			t.insertDefaultValues();
			t.save(name);
			return t;
		} else {
			return JsonManager.getInstance().getGson().fromJson(FileUtils.readFromFile(FileUtils.getFile(name)),
			                                                    clazz);
		}
	}

}
