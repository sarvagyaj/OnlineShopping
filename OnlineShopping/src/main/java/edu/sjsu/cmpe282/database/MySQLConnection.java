package edu.sjsu.cmpe282.database;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MySQLConnection {

	private static MySQLConnection connection;
	private DataSource dataSource;

	private MySQLConnection(String jndiname) {
		try {
			dataSource = (DataSource) new InitialContext()
					.lookup("java:comp/env/" + jndiname);
		} catch (NamingException e) {
			e.printStackTrace();
			System.out.println("JNDI is missing");
		}
	}

	public static MySQLConnection getInstance() {
		if (connection == null) {
			synchronized (MySQLConnection.class) {
				if (connection == null) {
					connection = new MySQLConnection("jdbc/rds");
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
