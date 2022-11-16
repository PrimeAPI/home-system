package de.primeapi.landaniatest.homesystem.common.io;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @author Lukas S. PrimeAPI
 * created on 14.11.2022
 * crated for Landania-home-system
 */
public class FileUtils {


	protected static final String BASE_URL = "plugins" + File.separator + "home-system" + File.separator;


	/**
	 * @param name The name of the Config (filename without filetype)
	 * @return Whether a file associated with that name exists
	 */
	public static boolean isFileExisting(String name) {
		File file = new File(BASE_URL + name + ".json");
		return file.exists();
	}

	/**
	 * @param name The name of the Config (filename without filetype)
	 * @return The created File
	 */
	public static File createFile(String name) throws IOException {
		File file = new File(BASE_URL + name + ".json");
		if (!file.exists()) {
			file.getParentFile().mkdirs();
			file.createNewFile();
		}
		return file;
	}

	/**
	 * @param name The name of the Config (filename without filetype)
	 * @return The File. Null if not existing
	 */
	public static File getFile(String name) {
		File file = new File(BASE_URL + name + ".json");
		return file.exists() ? file : null;
	}


	/**
	 * writes a String to a File
	 *
	 * @param file    The File to write to
	 * @param content The String to write
	 */
	public static void writeToFile(File file, String content) throws IOException {
		OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8);
		writer.write(content);
		writer.close();
	}

	/**
	 * @param file The File to read from
	 * @return The String read from the File
	 */
	public static String readFromFile(File file) throws IOException {
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
		StringBuilder builder = new StringBuilder();
		String line;
		while ((line = reader.readLine()) != null) {
			builder.append(line);
		}
		reader.close();
		return builder.toString();
	}


}
