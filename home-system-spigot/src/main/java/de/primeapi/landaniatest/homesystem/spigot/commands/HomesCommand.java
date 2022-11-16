package de.primeapi.landaniatest.homesystem.spigot.commands;

import de.primeapi.landaniatest.homesystem.api.exceptions.NotLoadedException;
import de.primeapi.landaniatest.homesystem.common.HomeMessaging;
import de.primeapi.landaniatest.homesystem.spigot.HomeSystem;
import de.primeapi.landaniatest.homesystem.spigot.gui.HomesGUI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * @author Lukas S. PrimeAPI
 * created on 16.11.2022
 * crated for Landania-home-system
 */
public class HomesCommand implements CommandExecutor {

	@Override
	public boolean onCommand(
			@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s,
			@NotNull String[] strings
	                        ) {
		if (!(commandSender instanceof Player player)) return false;
		if (!player.hasPermission("homesystem.homes")) {
			player.sendMessage(HomeMessaging.getMessage("no-perms"));
			return false;
		}

		try {
			new HomesGUI(HomeSystem.getInstance().getHomeAPI().getHomes(player)).open(player);
		} catch (NotLoadedException e) {
			player.sendMessage(HomeMessaging.getMessage("not-loaded"));
			return true;
		}


		return true;
	}

}
