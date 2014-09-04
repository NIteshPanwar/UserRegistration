package com.snailark.userregistration.db.doa;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.snailark.userregistration.db.ConnectionFactory;
import com.snailark.userregistration.exception.ExceptionCategory;
import com.snailark.userregistration.exception.UserRegistrationException;
import com.snailark.userregistration.model.RegisteredUserVOAction;


	public class RegisteredUserDAO {

	public boolean createRegisteredUser(RegisteredUserVOAction requestedVO) throws UserRegistrationException {
		PreparedStatement preparedStatement = null;
		ConnectionFactory connectionFactory = new ConnectionFactory();
		Connection connection = connectionFactory.getConnection();
		boolean created;
		try {
			preparedStatement = connection.prepareStatement("insert into REGISTERED_USER (FIRST_NAME, LAST_NAME, GENDER, DATE_OF_BIRTH, EMAIL, CITY, PHONE_NUMBER) values(?, ?, ?, ?, ?, ?, ?)");
			preparedStatement.setString(1, requestedVO.getFirstName());
			preparedStatement.setString(2, requestedVO.getLastName());
			preparedStatement.setString(3, requestedVO.getGender());
			preparedStatement.setDate(4, new java.sql.Date(requestedVO.getDateOfBirth().getTime()));
			preparedStatement.setString(5, requestedVO.getEmail());
			preparedStatement.setString(6, requestedVO.getCity());
			preparedStatement.setLong(7, requestedVO.getPhoneNumber());
			created = preparedStatement.execute();
		} catch (SQLException e) {
			throw new UserRegistrationException(ExceptionCategory.SYSTEM);
		} finally {
			try {
				if(preparedStatement != null) { 
					preparedStatement.close();
				}
			} catch(SQLException e) {
				throw new UserRegistrationException(ExceptionCategory.SYSTEM);
			}
			
				connectionFactory.closeConnection(connection);
			
		}
		return created;
	}
	
	public List<RegisteredUserVOAction> getRegisteredUser(RegisteredUserVOAction registeredUserVO) throws UserRegistrationException{
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ConnectionFactory connectionFactory = new ConnectionFactory();
		List<RegisteredUserVOAction> dbrecord = new ArrayList<RegisteredUserVOAction>();
		Connection connection = connectionFactory.getConnection();
		boolean whereClause = false;
		String sqlquery = "select * from REGISTERED_USER";
		if(registeredUserVO.getEmail() != null && !"".equals(registeredUserVO.getEmail())) {
			sqlquery += " where EMAIL like '%"+ registeredUserVO.getEmail() + "%'";
			whereClause = true;
		}
		if(registeredUserVO.getFirstName() != null && !"".equals(registeredUserVO.getFirstName())) {
			if(whereClause == false){
				sqlquery += " where FIRST_NAME like '%"+ registeredUserVO.getFirstName() +"%'";
				whereClause = true;
			} else {
				sqlquery += " and FIRST_NAME like '%"+ registeredUserVO.getFirstName() +"%'";
			}
		} 
		if(registeredUserVO.getLastName() != null && !"".equals(registeredUserVO.getLastName())) {
			if(whereClause == false){
				sqlquery += " where LAST_NAME like '%"+ registeredUserVO.getLastName() + "%'";
		
			} else {
				sqlquery += " and LAST_NAME like '%"+ registeredUserVO.getLastName() + "%'";
		
			}
		}
			
		try {
			preparedStatement = connection.prepareStatement(sqlquery);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				RegisteredUserVOAction registereduserVO = new RegisteredUserVOAction();
				registereduserVO.setUserId(resultSet.getInt("USER_ID"));
				registereduserVO.setFirstName(resultSet.getString("FIRST_NAME"));
				registereduserVO.setLastName(resultSet.getString("LAST_NAME"));
				registereduserVO.setGender(resultSet.getString("GENDER"));
				registereduserVO.setDateOfBirth(resultSet.getDate("DATE_OF_BIRTH"));
				registereduserVO.setEmail(resultSet.getString("EMAIL"));
				registereduserVO.setCity(resultSet.getString("CITY"));
				registereduserVO.setPhoneNumber(resultSet.getLong("PHONE_NUMBER"));
				dbrecord.add(registereduserVO);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new UserRegistrationException(ExceptionCategory.SYSTEM);
			
		}
		if(resultSet != null){
			try {
				resultSet.close();
			} catch (SQLException e) {
				throw new UserRegistrationException(ExceptionCategory.SYSTEM);
			}
		}
		if(preparedStatement !=null){
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				throw new UserRegistrationException(ExceptionCategory.SYSTEM);
			}
		}
		connectionFactory.closeConnection(connection);
		return dbrecord;
	}
	
	public RegisteredUserVOAction getRegisteredUserByEmailOrUserID(RegisteredUserVOAction requestVO) throws UserRegistrationException {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ConnectionFactory connectionFactory = new ConnectionFactory();
		RegisteredUserVOAction registeredUserVO = null;
		String sqlQuery = "select * from REGISTERED_USER";
		if(requestVO.getEmail() != null) {
			sqlQuery += " where EMAIL='" + requestVO.getEmail() + "'";
		}
		if(requestVO.getUserId() != 0){
			sqlQuery += " where USER_ID = '" + requestVO.getUserId() +"'";
		}
		Connection connection = null;
		try {
			connection = connectionFactory.getConnection();
			preparedStatement = connection.prepareStatement(sqlQuery);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				registeredUserVO = new RegisteredUserVOAction();
				registeredUserVO.setUserId(resultSet.getInt("USER_ID"));
				registeredUserVO.setFirstName(resultSet.getString("FIRST_NAME"));
				registeredUserVO.setLastName(resultSet.getString("LAST_NAME"));
				registeredUserVO.setGender(resultSet.getString("GENDER"));
				registeredUserVO.setDateOfBirth(resultSet.getDate("DATE_OF_BIRTH"));
				registeredUserVO.setEmail(resultSet.getString("EMAIL"));
				registeredUserVO.setCity(resultSet.getString("CITY"));
				registeredUserVO.setPhoneNumber(resultSet.getLong("PHONE_NUMBER"));
			}
			
		} catch (SQLException e) {
			throw new UserRegistrationException(ExceptionCategory.SYSTEM);
		} finally {
			if(resultSet != null){
				try {
					resultSet.close();
				} catch (SQLException e) {
					throw new UserRegistrationException(ExceptionCategory.SYSTEM);
				}
			}
			if(preparedStatement !=null){
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					throw new UserRegistrationException(ExceptionCategory.SYSTEM);
				}
			}
				connectionFactory.closeConnection(connection);
			
		}
		return registeredUserVO;
	}
	
	
	public boolean updateRegisteredUser(RegisteredUserVOAction requestedVO) throws UserRegistrationException {
		PreparedStatement preparedStatement = null;
		ConnectionFactory connectionFactory = new ConnectionFactory();
		Connection connection = connectionFactory.getConnection();
		boolean created;
		try {
			preparedStatement = connection.prepareStatement("update REGISTERED_USER set FIRST_NAME = ?, LAST_NAME = ?, GENDER = ?, DATE_OF_BIRTH = ?, CITY = ?, PHONE_NUMBER = ? where USER_ID = ?");
			preparedStatement.setString(1, requestedVO.getFirstName());
			preparedStatement.setString(2, requestedVO.getLastName());
			preparedStatement.setString(3, requestedVO.getGender());
			preparedStatement.setDate(4, new java.sql.Date(requestedVO.getDateOfBirth().getTime()));
			preparedStatement.setString(5, requestedVO.getCity());
			preparedStatement.setLong(6, requestedVO.getPhoneNumber());
			preparedStatement.setLong(7, requestedVO.getUserId());
			created = preparedStatement.execute();
		} catch (SQLException e) {
			throw new UserRegistrationException(ExceptionCategory.SYSTEM);
		} finally {
			try {
				if(preparedStatement != null) { 
					preparedStatement.close();
				}
			} catch(SQLException e) {
				throw new UserRegistrationException(ExceptionCategory.SYSTEM);
			}
			
				connectionFactory.closeConnection(connection);
			
		}
		return created;
	}

}
