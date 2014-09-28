package edu.sjsu.cmpe282.database;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBConnection {

	private static DBConnection connection;
	private DataSource dataSource;

	private DBConnection(String jndiname) {
		try {
			dataSource = (DataSource) new InitialContext()
					.lookup("java:comp/env/" + jndiname);
		} catch (NamingException e) {
			e.printStackTrace();
			System.out.println("JNDI is missing");
		}
	}

	public static DBConnection getInstance() {
		if (connection == null) {
			synchronized (DBConnection.class) {
				if (connection == null) {
					connection = new DBConnection("jdbc/db");
				}
			}
		}
		return connection;
	}

	public Connection getConnection() {
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
