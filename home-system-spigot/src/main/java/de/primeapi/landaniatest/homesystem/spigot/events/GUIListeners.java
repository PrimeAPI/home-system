package de.primeapi.landaniatest.homesystem.spigot.events;

import de.primeapi.landaniatest.homesystem.spigot.gui.Clickable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

/**
 * @author Lukas S. PrimeAPI
 * created on 16.11.2022
 * crated for Landania-home-system
 */
public class GUIListeners implements Listener {

	public static HashMap<Inventory, HashMap<ItemStack, Clickable>> openedInventories = new HashMap<>();


	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		if (!(e.getWhoClicked() instanceof Player player)) return;
		if (e.getClickedInventory() == null) return;
		if (openedInventories.containsKey(e.getClickedInventory())) {
			e.setCancelled(true);
			if (e.getCurrentItem() == null) return;
			if (openedInventories.get(e.getClickedInventory()).containsKey(e.getCurrentItem())) {
				Clickable clickable = openedInventories.get(e.getClickedInventory()).get(e.getCurrentItem());
				if (clickable != null) clickable.onClick(player, e.getCurrentItem());
			}
		}
	}

	@EventHandler
	public void onInventoryClose(InventoryCloseEvent e) {
		openedInventories.remove(e.getInventory());
	}

}
