package edu.sjsu.cmpe282.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import edu.sjsu.cmpe282.constants.Constants;
import edu.sjsu.cmpe282.database.MySQLConnection;
import edu.sjsu.cmpe282.dto.User;

public class UserDao {
	private Connection connection = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	public boolean addUser(User user) {
		try {
			connection = MySQLConnection.getInstance().getConnection();
			statement = connection.createStatement();
			String query = "INSERT INTO `"+Constants.SCHEMA_NAME+"`.`user` (`first_name`, `last_name`, `email_id`, `password`) VALUES ('"
					+ user.getFirstName()
					+ "', '"
					+ user.getLastName()
					+ "', '"
					+ user.getEmail()
					+ "', '"
					+ user.getPassword()
					+ "');";
			statement.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean login(String userid, String password) {
		if(validatePassword(userid, password)) {
			return true;
		}
		return false;
		
	
		
		
	}
	
	 public boolean validatePassword(String userid, String password){
		  String originalPassword = null;
		  	try {
		  		connection = MySQLConnection.getInstance().getConnection();
		  		preparedStatement = connection.prepareStatement("Select password from `"+Constants.SCHEMA_NAME +"`.`user` where email_id=?");
		  		preparedStatement.setString(1, userid);
		  		resultSet=preparedStatement.executeQuery();
		  		
		  		resultSet.next();
		  		originalPassword = resultSet.getString("password");
		  		System.out.println("Password from db : "+ originalPassword );
		  		System.out.println("Password entered : "+password);
		  		} catch (SQLException e) {
		  			e.printStackTrace();
		  		}

		  		return password.equals(originalPassword);
		  }
	 
	 public String getFirstName(String userid){
		  String firstName = null;
		  	try {
		  		connection = MySQLConnection.getInstance().getConnection();
		  		preparedStatement = connection.prepareStatement("Select first_name from `"+Constants.SCHEMA_NAME+"`.`user` where email_id=?");
		  		preparedStatement.setString(1, userid);
		  		resultSet=preparedStatement.executeQuery();
		  	
		  		resultSet.next();
		  		firstName = resultSet.getString("first_name");
		  		} catch (SQLException e) {
		  			e.printStackTrace();
		  		}

		  		return firstName;
		  }
	 
	 public User getUser(String userid){
		  User user = null;
		  	try {
		  		connection = MySQLConnection.getInstance().getConnection();
		  		preparedStatement = connection.prepareStatement("Select first_name,last_name, password from `"+Constants.SCHEMA_NAME+"`.`user` where email_id=?");
		  		preparedStatement.setString(1, userid);
		  		resultSet=preparedStatement.executeQuery();
		  	
		  		resultSet.next();
		  		user = new User(resultSet.getString("first_name"),resultSet.getString("last_name"),userid,resultSet.getString("password"));
		  		} catch (SQLException e) {
		  			e.printStackTrace();
		  		}

		  		return user;
		  }
}
