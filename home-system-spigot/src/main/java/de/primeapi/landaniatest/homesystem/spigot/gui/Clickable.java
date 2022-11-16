package de.primeapi.landaniatest.homesystem.spigot.gui;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * @author Lukas S. PrimeAPI
 * created on 16.11.2022
 * crated for Landania-home-system
 */
public interface Clickable {

	void onClick(Player player, ItemStack itemStack);

}
