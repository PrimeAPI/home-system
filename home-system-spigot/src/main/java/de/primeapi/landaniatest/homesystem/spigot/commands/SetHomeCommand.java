package de.primeapi.landaniatest.homesystem.spigot.commands;

import de.primeapi.landaniatest.homesystem.api.exceptions.NotLoadedException;
import de.primeapi.landaniatest.homesystem.common.HomeMessaging;
import de.primeapi.landaniatest.homesystem.spigot.HomeSystem;
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
public class SetHomeCommand implements CommandExecutor {
	@Override
	public boolean onCommand(
			@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s,
			@NotNull String[] strings
	                        ) {
		if (!(commandSender instanceof Player player)) return false;
		if (!player.hasPermission("homesystem.sethome")) {
			player.sendMessage(HomeMessaging.getMessage("no-perms"));
			return false;
		}
		if (strings.length == 0) {
			player.sendMessage(HomeMessaging.getMessage("sethome-usage"));
			return true;
		}

		if (strings[0].equals("*")) {
			player.sendMessage(HomeMessaging.getMessage("sethome-invalid"));
			return true;
		}

		if (strings[0].length() > 10) {
			player.sendMessage(HomeMessaging.getMessage("sethome-lenght"));
			return true;
		}

		try {
			if (HomeSystem.getInstance().getHomeAPI().getHome(player, strings[0]) != null) {
				player.sendMessage(HomeMessaging.getMessage("sethome-already-exists"));
				return true;
			}
		} catch (NotLoadedException e) {
			player.sendMessage(HomeMessaging.getMessage("not-loaded"));
			return true;
		}

		HomeSystem.getInstance().getHomeAPI().addHome(player, strings[0], player.getLocation());
		player.sendMessage(HomeMessaging.getMessage("sethome-confirm", strings[0]));


		return true;
	}
}
