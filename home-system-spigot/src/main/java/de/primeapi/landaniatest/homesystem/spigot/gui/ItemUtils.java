package de.primeapi.landaniatest.homesystem.spigot.gui;

import com.destroystokyo.paper.profile.PlayerProfile;
import com.destroystokyo.paper.profile.ProfileProperty;
import de.primeapi.landaniatest.homesystem.api.caching.Home;
import de.primeapi.landaniatest.homesystem.common.HomeMessaging;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author Lukas S. PrimeAPI
 * created on 16.11.2022
 * crated for Landania-home-system
 */
public class ItemUtils {


	public static ItemStack createHomeItemStack(Home home) {
		ItemStack itemStack = new ItemStack(Material.PAPER);
		ItemMeta itemMeta = itemStack.getItemMeta();
		itemMeta.displayName(Component.text(HomeMessaging.getMessageWithoutPrefix("gui-item-title", home.getName())));
		itemMeta.lore(
				Arrays.stream(new Component[]{Component.text(HomeMessaging.getMessageWithoutPrefix("gui-item-lore"))})
				      .collect(Collectors.toList()));
		itemStack.setItemMeta(itemMeta);
		return itemStack;
	}

	public static ItemStack createSkull(String name, String url) {
		ItemStack head = new ItemStack(Material.PLAYER_HEAD);

		SkullMeta headMeta = (SkullMeta) head.getItemMeta();
		headMeta.displayName(Component.text(name));
		PlayerProfile profile = Bukkit.createProfile(UUID.randomUUID(), null);

		profile.getProperties().add(new ProfileProperty("textures", url));
		headMeta.setPlayerProfile(profile);
		head.setItemMeta(headMeta);
		return head;
	}


}
