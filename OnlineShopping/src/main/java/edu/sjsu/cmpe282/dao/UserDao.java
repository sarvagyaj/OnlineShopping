package edu.sjsu.cmpe282.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

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
			if (!validateEmail(user.getEmail())) {
				return false;
			}
			connection = MySQLConnection.getInstance().getConnection();
			statement = connection.createStatement();
			String query = "INSERT INTO `"
					+ Constants.SCHEMA_NAME
					+ "`.`user` (`first_name`, `last_name`, `email_id`, `password`,`is_admin`,`last_login_time`) VALUES ('"
					+ user.getFirstName() + "', '" + user.getLastName()
					+ "', '" + user.getEmail() + "', '" + user.getPassword()
					+ "','0','"+System.currentTimeMillis()+"');";
			statement.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	public long login(String userid, String password) {
		long lastLoginTime=0L;
		if (validatePassword(userid, password)) {
			connection = MySQLConnection.getInstance().getConnection();
			try {
				statement = connection.createStatement();
				preparedStatement = connection
						.prepareStatement("Select last_login_time from `"
								+ Constants.SCHEMA_NAME
								+ "`.`user` where email_id=?");
				preparedStatement.setString(1, userid);
				resultSet = preparedStatement.executeQuery();

				resultSet.next();
				lastLoginTime = resultSet.getLong("last_login_time");
				
				String query = "UPDATE `" + Constants.SCHEMA_NAME
						+ "`.`user` SET `last_login_time` ='"
						+ System.currentTimeMillis() + "' where `email_id`='"+userid+"';";
				statement.executeUpdate(query);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return lastLoginTime;
		}
		return -1L;

	}

	public boolean validateEmail(String userid) {
		try {
			connection = MySQLConnection.getInstance().getConnection();
			preparedStatement = connection
					.prepareStatement("Select email_id from `"
							+ Constants.SCHEMA_NAME
							+ "`.`user` where email_id=?");
			preparedStatement.setString(1, userid);
			resultSet = preparedStatement.executeQuery();

			if (!resultSet.next()) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public boolean validatePassword(String userid, String password) {
		String originalPassword = null;
		try {
			connection = MySQLConnection.getInstance().getConnection();
			preparedStatement = connection
					.prepareStatement("Select password from `"
							+ Constants.SCHEMA_NAME
							+ "`.`user` where email_id=?");
			preparedStatement.setString(1, userid);
			resultSet = preparedStatement.executeQuery();

			resultSet.next();
			originalPassword = resultSet.getString("password");
			System.out.println("Password from db : " + originalPassword);
			System.out.println("Password entered : " + password);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return password.equals(originalPassword);
	}

	public String getFirstName(String userid) {
		String firstName = null;
		try {
			connection = MySQLConnection.getInstance().getConnection();
			preparedStatement = connection
					.prepareStatement("Select first_name from `"
							+ Constants.SCHEMA_NAME
							+ "`.`user` where email_id=?");
			preparedStatement.setString(1, userid);
			resultSet = preparedStatement.executeQuery();

			resultSet.next();
			firstName = resultSet.getString("first_name");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return firstName;
	}

	public User getUser(String userid, long time) {
		User user = null;
		try {
			connection = MySQLConnection.getInstance().getConnection();
			preparedStatement = connection
					.prepareStatement("Select first_name,last_name, password,is_admin from `"
							+ Constants.SCHEMA_NAME
							+ "`.`user` where email_id=?");
			preparedStatement.setString(1, userid);
			resultSet = preparedStatement.executeQuery();

			resultSet.next();
			user = new User(resultSet.getString("first_name"),
					resultSet.getString("last_name"), userid,
					resultSet.getString("password"),
					resultSet.getShort("is_admin"),
					new Date(time));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user;
	}
}
