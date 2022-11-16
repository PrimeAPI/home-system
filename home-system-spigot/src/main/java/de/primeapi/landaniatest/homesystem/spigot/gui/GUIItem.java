package de.primeapi.landaniatest.homesystem.spigot.gui;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.inventory.ItemStack;

/**
 * @author Lukas S. PrimeAPI
 * created on 16.11.2022
 * crated for Landania-home-system
 */
@AllArgsConstructor
@Getter
public class GUIItem {

	private ItemStack itemStack;
	private Clickable clickable;

}
