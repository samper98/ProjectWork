package org.generation.italy.newEnteSportivo2.servlet;

import java.sql.Connection;

public abstract class ADao {

	protected Connection jdbcConnectionToDatabase;

	public ADao(Connection jdbcConnectionToDatabase) {
		this.jdbcConnectionToDatabase = jdbcConnectionToDatabase;
	}

}
