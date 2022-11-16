package de.primeapi.landaniatest.homesystem.api.sql.utils;

import de.primeapi.landaniatest.homesystem.api.sql.Database;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Lukas S. PrimeAPI
 * created on 08.03.2022
 * crated for SQLAdapter
 */
@RequiredArgsConstructor
public class SelectStatement {

	@NonNull
	private Database database;
	@NonNull
	private String query;
	private List<Object> parameters = new LinkedList<>();

	public SelectStatement parameters(Object... parameters) {
		this.parameters = Arrays.stream(parameters).collect(Collectors.toList());
		return this;
	}

	public SelectStatement parameter(Object parameter) {
		parameters.add(parameter);
		return this;
	}


	public <T> Collector<T> execute(Class<T> cls) {
		try {
			PreparedStatement st = database.getConnection().prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,
			                                                                 ResultSet.CONCUR_UPDATABLE
			                                                                );
			int i = 1;
			for (Object parameter : parameters) {
				st.setObject(i, parameter);
				i++;
			}

			return new Collector<>(st.executeQuery(), database, cls);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}


}
