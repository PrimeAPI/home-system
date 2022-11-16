package de.primeapi.landaniatest.homesystem.common.configurations;

import de.primeapi.landaniatest.homesystem.common.oop.Configuration;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

/**
 * @author Lukas S. PrimeAPI
 * created on 16.11.2022
 * crated for Landania-home-system
 */
@Getter
@Setter
public class MessagesConfiguration extends Configuration {

	private String prefix;

	private HashMap<String, String> messages;


	@Override
	public void insertDefaultValues() {
		setPrefix("§8[§6HomeSystem§8]");
		setMessages(new HashMap<>());
		messages.put("no-perms", "§c Du hast keine Berechtigungen für diesen Befehl!");
		messages.put("not-loaded", "§c Deine Homes wurden noch nicht geladen. Versuche es in einigen Sekunden erneut" +
				".");
		messages.put("home-confirm", "§7 Du wurdest §aerfolgreich §7zu deinem Home §7{0}§7 teleportiert!");
		messages.put("home-usage", "§7 Verwende: §e/home <Name>");
		messages.put("home-not-found", "§7 Du hast kein Home mit dem Namen §e{0}§7!");
		messages.put("delete-confirm", "§7 Du hast §aerfolgreich §7zu dein Home §7{0} §7cgelöscht§7!");
		messages.put("delete-usage", "§7 Verwende: §e/delhome <Name>§7");
		messages.put("delete-all-confirm", "§7 Du hast §calle Homes gelöscht§7!");
		messages.put("sethome-confirm", "§7 Du hast §aerfolgreich §7dein Home §e{0} §7gesetzt§7!");
		messages.put("sethome-lenght", "§c Der gewählte Name ist zu lang! (max. 10 Zeichen)");
		messages.put(
				"sethome-already-exists", "§7 Du hast bereits ein Home mit diesem Namen§7! Nutze §e/delhome <Name>");
		messages.put("sethome-usage", "§7 Verwende: §e/sethome <Name>");
		messages.put("sethome-invalid", "§cDu darfst diesen Namen nicht verwenden!");
		messages.put("gui-title", "§aDeine Homes");
		messages.put("gui-item-title", "§e{0}");
		messages.put("gui-item-lore", "§7Klicke, um dich zum Home zu teleportieren");
		messages.put("gui-delete-name", "§cAlle Homes löschen");
	}
}
