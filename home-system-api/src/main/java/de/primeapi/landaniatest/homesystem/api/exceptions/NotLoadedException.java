package de.primeapi.landaniatest.homesystem.api.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Lukas S. PrimeAPI
 * created on 16.11.2022
 * crated for Landania-home-system
 * <p>
 * Exception to throw when a home is not loaded jet and thererfore is not in the cache
 */
@AllArgsConstructor
@Getter
public class NotLoadedException extends Throwable {
	String message;
}
