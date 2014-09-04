package com.snailark.userregistration.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.snailark.userregistration.exception.ExceptionCategory;
import com.snailark.userregistration.exception.UserRegistrationException;

public class ConnectionFactory {

	
	public Connection getConnection() throws UserRegistrationException {
		Connection connection =null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost/userregistration?user=root&password=root");
		} catch (ClassNotFoundException e) {
			throw new UserRegistrationException(ExceptionCategory.SYSTEM);
		} catch (SQLException e) {
			throw new UserRegistrationException(ExceptionCategory.SYSTEM);
		}
		return connection;
	}
	
	
	public void closeConnection(Connection connection) throws UserRegistrationException {
		if(connection != null) { 
			try {
				connection.close();
			} catch (SQLException e) {
				throw new UserRegistrationException(ExceptionCategory.SYSTEM);
			}	
		}
	}
}
