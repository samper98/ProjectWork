package org.generation.italy.newEnteSportivo2.model_jdbc.dao;

import java.sql.Connection;

public abstract class ADao {

	protected Connection jdbcConnectionToDatabase;

	public ADao(Connection jdbcConnectionToDatabase) {
		this.jdbcConnectionToDatabase = jdbcConnectionToDatabase;
	}

}
