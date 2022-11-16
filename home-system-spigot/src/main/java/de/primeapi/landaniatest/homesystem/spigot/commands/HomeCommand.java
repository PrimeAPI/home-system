package de.primeapi.landaniatest.homesystem.spigot.commands;

import de.primeapi.landaniatest.homesystem.api.caching.Home;
import de.primeapi.landaniatest.homesystem.api.exceptions.NotLoadedException;
import de.primeapi.landaniatest.homesystem.common.HomeMessaging;
import de.primeapi.landaniatest.homesystem.spigot.HomeSystem;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * @author Lukas S. PrimeAPI
 * created on 16.11.2022
 * crated for Landania-home-system
 */
public class HomeCommand implements CommandExecutor, TabCompleter {
	@Nullable
	static List<String> getStrings(@NotNull CommandSender commandSender, @NotNull String[] strings) {
		if (!(commandSender instanceof Player player)) return null;
		try {
			return HomeSystem.getInstance().getHomeAPI().getHomes(player).stream().map(Home::getName)
			                 .filter(name -> name.startsWith(strings[0]))
			                 .toList();
		} catch (NotLoadedException ignored) {
			return null;
		}
	}

	@Override
	public boolean onCommand(
			@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s,
			@NotNull String[] strings
	                        ) {
		if (!(commandSender instanceof Player player)) return false;

		if (!player.hasPermission("homesystem.home")) {
			player.sendMessage(HomeMessaging.getMessage("no-perms"));
			return false;
		}

		if (strings.length == 0) {
			player.sendMessage(HomeMessaging.getMessage("home-usage"));
			return true;
		}

		try {
			Home home = HomeSystem.getInstance().getHomeAPI().getHome(player, strings[0]);
			if (home == null) {
				player.sendMessage(HomeMessaging.getMessage("home-not-found", strings[0]));
				return true;
			}
			player.teleport(home.getPosition());
			player.sendMessage(HomeMessaging.getMessage("home-confirm", strings[0]));
		} catch (NotLoadedException e) {
			player.sendMessage(HomeMessaging.getMessage("not-loaded"));
			return true;
		}


		return true;
	}

	@Override
	public @Nullable List<String> onTabComplete(
			@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s,
			@NotNull String[] strings
	                                           ) {
		return getStrings(commandSender, strings);
	}
}
