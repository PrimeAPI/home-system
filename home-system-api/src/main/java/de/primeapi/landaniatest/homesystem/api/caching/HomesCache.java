package de.primeapi.landaniatest.homesystem.api.caching;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Lukas S. PrimeAPI
 * created on 15.11.2022
 * crated for Landania-home-system
 */
public class HomesCache {

	ConcurrentHashMap<UUID, Set<Home>> cache = new ConcurrentHashMap<>();

	/**
	 * Method to get cached Homes
	 *
	 * @param uuid The uuid of the player
	 * @return The cached homes
	 */
	public Set<Home> getHomes(UUID uuid) {
		return cache.get(uuid);
	}

	/**
	 * Method to add a home to the cache
	 *
	 * @param home The home to add
	 */
	public void addHome(Home home) {
		cache.computeIfAbsent(home.getOwner(), uuid -> ConcurrentHashMap.newKeySet()).add(home);
	}

	/**
	 * Method to add a set of homes to the cache
	 *
	 * @param homes The homes to add
	 */
	public void addHomes(UUID uuid, Set<Home> homes) {
		if (homes.size() > 0) {homes.forEach(this::addHome);} else cache.put(uuid, new HashSet<>());
	}


	/**
	 * Method to remove a home from the cache
	 *
	 * @param home The home to remove
	 */
	public void removeHome(Home home) {
		cache.computeIfAbsent(home.getOwner(), uuid -> ConcurrentHashMap.newKeySet()).remove(home);
	}

	public void removePlayer(UUID uuid) {
		cache.remove(uuid);
	}



}
