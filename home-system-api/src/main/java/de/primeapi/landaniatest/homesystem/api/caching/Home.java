package de.primeapi.landaniatest.homesystem.api.caching;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.HashMap;
import java.util.UUID;

/**
 * @author Lukas S. PrimeAPI
 * created on 15.11.2022
 * crated for Landania-home-system
 */
@Data
@AllArgsConstructor
public class Home {


	private int id;
	private UUID owner;
	private String name;
	private Location position;

	public static Home fromDatabase(HashMap<String, Object> map) {
		World world = Bukkit.getWorld(map.get("pos_world").toString());
		if (world == null) return null;
		Location location = new Location(
				world,
				(double) map.get("pos_x"),
				(double) map.get("pos_y"),
				(double) map.get("pos_z"),
				(float) map.get("pos_yaw"),
				(float) map.get("pos_pitch")
		);

		return new Home(
				(int) map.get("id"),
				UUID.fromString(map.get("uuid").toString()),
				map.get("name").toString(),
				location
		);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Home) {
			return ((Home) obj).getId() == id;
		}
		return false;
	}


}
