package de.primeapi.landaniatest.homesystem.api.sql.utils;

import de.primeapi.landaniatest.homesystem.api.sql.Database;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.SneakyThrows;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.util.*;

/**
 * @author Lukas S. PrimeAPI
 * created on 08.03.2022
 * crated for SQLAdapter
 */
@AllArgsConstructor
public class Collector<T> {

	@NonNull
	private ResultSet resultSet;
	@NonNull
	private Database database;
	private Class<T> type;

	/**
	 * @return The Set of the first column of all rows
	 */
	@SneakyThrows
	private List<T> getAsSetRaw() {
		List<T> list = new ArrayList<>();
		resultSet.beforeFirst();
		while (resultSet.next()) {
			list.add(type.cast(resultSet.getObject(1)));
		}
		return list;
	}

	public Retriever<List<T>> getAsSet() {
		return new Retriever<>(this::getAsSetRaw);
	}


	/**
	 * @return The first column of the first row
	 */
	@SneakyThrows
	private T getRaw() {
		resultSet.beforeFirst();
		if (!resultSet.next()) return null;
		Object object = resultSet.getObject(1);
		if (object instanceof BigInteger) {
			return type.cast(((BigInteger) object).intValue());
		}
		return type.cast(resultSet.getObject(1));
	}

	public Retriever<T> get() {
		return new Retriever<>(this::getRaw);
	}

	/**
	 * @param index The index of the row
	 * @return Returns the row indicated the index of the first row
	 */
	@SneakyThrows
	private T getRaw(int index) {
		resultSet.beforeFirst();
		if (!resultSet.next()) return null;
		return type.cast(resultSet.getObject(index));
	}

	public Retriever<T> get(int index) {
		return new Retriever<>(() -> getRaw(index));
	}


	/**
	 * @param column The name of the column
	 * @return The value if the column given by {@code column} of the first row
	 */
	@SneakyThrows
	private T getRaw(String column) {
		resultSet.beforeFirst();
		if (!resultSet.next()) return null;
		return type.cast(resultSet.getObject(column));
	}

	public Retriever<T> get(String column) {
		return new Retriever<>(() -> getRaw(column));
	}


	/**
	 * @return Whether or not there is a next row
	 */
	@SneakyThrows
	private boolean isAnyRaw() {
		return resultSet.next();
	}

	public Retriever<Boolean> isAny() {
		return new Retriever<>(this::isAnyRaw);
	}

	/**
	 * @return All columns values in a row in an array
	 */
	@SneakyThrows
	private Object[] getRowDataRaw() {
		if (resultSet.isBeforeFirst()) resultSet.next();
		Object[] array = new Object[resultSet.getMetaData().getColumnCount()];
		for (int i = 0; i < array.length; i++) {
			array[i] = resultSet.getObject(i + 1);
		}
		return array;
	}

	public Retriever<Object[]> getRowData() {
		return new Retriever<>(this::getRowDataRaw);
	}

	/**
	 * @return All columns values in a row in an array
	 */
	@SneakyThrows
	private HashMap<String, Object> getRowHashMapRaw() {
		if (resultSet.isBeforeFirst()) resultSet.next();
		HashMap<String, Object> map = new HashMap<>();
		for (int i = 0; i < resultSet.getMetaData().getColumnCount(); i++) {
			map.put(resultSet.getMetaData().getColumnName(i + 1), resultSet.getObject(i + 1));
		}
		return map;
	}

	public Retriever<HashMap<String, Object>> getRowHashMap() {
		return new Retriever<>(this::getRowHashMapRaw);
	}


	/**
	 * @return All columns values in a row in an array
	 */
	@SneakyThrows
	private List<HashMap<String, Object>> getAllRowHashMapRaw() {
		if (resultSet.isBeforeFirst()) resultSet.beforeFirst();
		List<HashMap<String, Object>> list = new ArrayList<>();
		while (resultSet.next()) {
			HashMap<String, Object> map = new HashMap<>();
			for (int i = 0; i < resultSet.getMetaData().getColumnCount(); i++) {
				map.put(resultSet.getMetaData().getColumnName(i + 1), resultSet.getObject(i + 1));
			}
			list.add(map);
		}
		return list;
	}

	public Retriever<List<HashMap<String, Object>>> getAllRowHashMap() {
		return new Retriever<>(this::getAllRowHashMapRaw);
	}


	/**
	 * @return A Set of all rows' columns values in a row in an array
	 */
	@SneakyThrows
	private Set<Object[]> getAllRowDataRaw() {
		Set<Object[]> set = new HashSet<>();
		resultSet.beforeFirst();
		while (resultSet.next()) {
			set.add(getRowDataRaw());
		}
		return set;
	}


	@Deprecated
	public Retriever<Set<Object[]>> getAllRowDataAsync() {
		return new Retriever<>(this::getAllRowDataRaw);
	}

	public Retriever<Set<Object[]>> getAllRowData() {
		return new Retriever<>(this::getAllRowDataRaw);
	}


}
