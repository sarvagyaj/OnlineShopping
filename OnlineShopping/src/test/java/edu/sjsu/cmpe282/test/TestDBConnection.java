package edu.sjsu.cmpe282.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestDBConnection {

	public static void main(String[] args) {
		try {
		    System.out.println("Loading driver...");
		    Class.forName("com.mysql.jdbc.Driver");
		    System.out.println("Driver loaded!");
		} catch (ClassNotFoundException e) {
		    throw new RuntimeException("Cannot find the driver in the classpath!", e);
		}
		
		Connection connection = null;
        try {
            connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/test_shopping", "root", "root");
            System.out.println("SQL Connection to database established!");
 
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            return;
        } finally {
            try
            {
                if(connection != null)
                    connection.close();
                System.out.println("Connection closed !!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

	}

}
