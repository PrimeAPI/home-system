package de.primeapi.landaniatest.homesystem.api.sql.utils;

import de.primeapi.landaniatest.homesystem.api.sql.Database;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
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
public class UpdateStatement {


	@NonNull
	private Database database;
	@NonNull
	private String query;


	private List<Object> parameters = new LinkedList<>();

	public UpdateStatement parameters(Object... parameters) {
		this.parameters = Arrays.stream(parameters).collect(Collectors.toList());
		return this;
	}

	public UpdateStatement parameter(Object parameter) {
		parameters.add(parameter);
		return this;
	}


	@SneakyThrows
	public void execute() {
		PreparedStatement st = database.getConnection().prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,
		                                                                 ResultSet.CONCUR_UPDATABLE
		                                                                );
		int i = 1;
		for (Object parameter : parameters) {
			st.setObject(i, parameter);
			i++;
		}
		st.executeUpdate();
	}

	@SneakyThrows
	public <T> Collector<T> returnGeneratedKeys(Class<T> type) {

		PreparedStatement st = database.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		int i = 1;
		for (Object parameter : parameters) {
			st.setObject(i, parameter);
			i++;
		}
		st.executeUpdate();
		return new Collector<>(st.getGeneratedKeys(), database, type);
	}


}
