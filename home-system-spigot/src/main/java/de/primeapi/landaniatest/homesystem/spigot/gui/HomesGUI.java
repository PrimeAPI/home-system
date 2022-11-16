package de.primeapi.landaniatest.homesystem.spigot.gui;

import de.primeapi.landaniatest.homesystem.api.caching.Home;
import de.primeapi.landaniatest.homesystem.common.HomeMessaging;
import de.primeapi.landaniatest.homesystem.spigot.events.GUIListeners;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Lukas S. PrimeAPI
 * created on 16.11.2022
 * crated for Landania-home-system
 */
public class HomesGUI {

	private HashMap<Integer, GUIItem> items = new HashMap<>();

	public HomesGUI(Set<Home> homes) {
		int i = 0;
		for (Home home : homes) {
			if (i > 44) break;
			items.put(i, new GUIItem(ItemUtils.createHomeItemStack(home), (player, itemStack) -> player.chat("/home " + home.getName())));
			i++;
		}

		for (int j = 45; j < 54; j++) {
			items.put(j, new GUIItem(new ItemStack(Material.GRAY_STAINED_GLASS_PANE), null));
		}

		items.put(49, new GUIItem(ItemUtils.createSkull(
				HomeMessaging.getMessageWithoutPrefix("gui-delete-name"),
				"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmViNTg4YjIxYTZmOThhZDFmZjRlMDg1YzU1MmRjYjA1MGVmYzljYWI0MjdmNDYwNDhmMThmYzgwMzQ3NWY3In19fQ=="
		                                               ), (player, itemStack) -> {
			player.chat("/delhome *");
			player.closeInventory();
		}));
	}

	public void open(Player player) {
		Inventory inventory = Bukkit.createInventory(
				null, 54, Component.text(HomeMessaging.getMessageWithoutPrefix("gui-title")));
		HashMap<ItemStack, Clickable> hashMap = new HashMap<>();
		for (Map.Entry<Integer, GUIItem> entry : items.entrySet()) {
			Integer integer = entry.getKey();
			GUIItem guiItem = entry.getValue();
			inventory.setItem(integer, guiItem.getItemStack());
			hashMap.put(guiItem.getItemStack(), guiItem.getClickable());
		}

		player.openInventory(inventory);
		GUIListeners.openedInventories.put(inventory, hashMap);

	}


}
