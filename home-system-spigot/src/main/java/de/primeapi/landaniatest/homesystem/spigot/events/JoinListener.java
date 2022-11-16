package de.primeapi.landaniatest.homesystem.spigot.events;

import de.primeapi.landaniatest.homesystem.spigot.HomeSystem;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * @author Lukas S. PrimeAPI
 * created on 16.11.2022
 * crated for Landania-home-system
 */
public class JoinListener implements Listener {


	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		HomeSystem.getInstance().getHomeAPI().loadHomes(e.getPlayer());
	}

}
