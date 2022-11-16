package de.primeapi.landaniatest.homesystem.spigot;

import de.primeapi.landaniatest.homesystem.api.HomeAPI;
import de.primeapi.landaniatest.homesystem.common.JsonManager;
import de.primeapi.landaniatest.homesystem.spigot.commands.DelHomeCommand;
import de.primeapi.landaniatest.homesystem.spigot.commands.HomeCommand;
import de.primeapi.landaniatest.homesystem.spigot.commands.HomesCommand;
import de.primeapi.landaniatest.homesystem.spigot.commands.SetHomeCommand;
import de.primeapi.landaniatest.homesystem.spigot.events.GUIListeners;
import de.primeapi.landaniatest.homesystem.spigot.events.JoinListener;
import de.primeapi.landaniatest.homesystem.spigot.events.QuitListener;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

/**
 * @author Lukas S. PrimeAPI
 * created on 14.11.2022
 * crated for Landania-home-system
 */
public class HomeSystem extends JavaPlugin {

	private static HomeSystem instance;
	@Getter
	private JsonManager jsonManager;
	@Getter
	private HomeAPI homeAPI;

	public static HomeSystem getInstance() {
		return instance;
	}

	@Override
	public void onEnable() {
		instance = this;
		this.jsonManager = new JsonManager(getLogger());
		this.homeAPI = new HomeAPI(jsonManager.getMySQLConfiguration(), getLogger());
		registerCommands();
		registerEvents();
	}

	@Override
	public void onDisable() {
		super.onDisable();
	}


	public void registerCommands() {
		{
			HomeCommand homeCommand = new HomeCommand();
			Objects.requireNonNull(getCommand("home")).setExecutor(homeCommand);
			Objects.requireNonNull(getCommand("home")).setTabCompleter(homeCommand);
		}
		{
			DelHomeCommand delHomeCommand = new DelHomeCommand();
			Objects.requireNonNull(getCommand("delhome")).setExecutor(delHomeCommand);
			Objects.requireNonNull(getCommand("delhome")).setTabCompleter(delHomeCommand);
		}
		Objects.requireNonNull(getCommand("sethome")).setExecutor(new SetHomeCommand());
		Objects.requireNonNull(getCommand("homes")).setExecutor(new HomesCommand());
	}

	public void registerEvents() {
		Bukkit.getPluginManager().registerEvents(new JoinListener(), this);
		Bukkit.getPluginManager().registerEvents(new QuitListener(), this);
		Bukkit.getPluginManager().registerEvents(new GUIListeners(), this);
	}


}
