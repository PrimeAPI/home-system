package de.primeapi.landaniatest.homesystem.spigot.events;

import de.primeapi.landaniatest.homesystem.spigot.HomeSystem;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * @author Lukas S. PrimeAPI
 * created on 16.11.2022
 * crated for Landania-home-system
 */
public class QuitListener implements Listener {


	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e) {
		//removing homes from the cache to save memory when multiple players are online
		HomeSystem.getInstance().getHomeAPI().unloadHomes(e.getPlayer());
	}

}
