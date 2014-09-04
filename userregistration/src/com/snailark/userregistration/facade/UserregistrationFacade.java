package com.snailark.userregistration.facade;

import java.util.List;

import com.snailark.userregistration.db.doa.RegisteredUserDAO;
import com.snailark.userregistration.exception.ExceptionCategory;
import com.snailark.userregistration.exception.UserRegistrationException;
import com.snailark.userregistration.model.RegisteredUserVOAction;

public class UserregistrationFacade{


	public List<RegisteredUserVOAction> showAllUser(RegisteredUserVOAction registeredUserVO) throws UserRegistrationException{
		
		RegisteredUserDAO registereduserDAO = new RegisteredUserDAO();
		List<RegisteredUserVOAction> dbusers =null;
		try {
			dbusers = registereduserDAO.getRegisteredUser(registeredUserVO);
		} catch (UserRegistrationException e) {
			throw new UserRegistrationException(ExceptionCategory.SYSTEM);
		}
		return dbusers;
	}
	
	public RegisteredUserVOAction showUser(RegisteredUserVOAction registeredUserVO) throws UserRegistrationException{
		
		RegisteredUserDAO registereduserDAO = new RegisteredUserDAO();
		RegisteredUserVOAction registeredUserVO2 =null;
		try {
			registeredUserVO2 = registereduserDAO.getRegisteredUserByEmailOrUserID(registeredUserVO);
		} catch (UserRegistrationException e) {
			throw new UserRegistrationException(ExceptionCategory.SYSTEM);
		}
		return registeredUserVO2;
	}
	
	public boolean createRegisteredUser(RegisteredUserVOAction requestVO) throws UserRegistrationException {
		boolean created = false;
		RegisteredUserDAO registereduserDAO = new RegisteredUserDAO();
		try {
			RegisteredUserVOAction dbUser = registereduserDAO.getRegisteredUserByEmailOrUserID(requestVO);
			if(dbUser == null) {
					created = registereduserDAO.createRegisteredUser(requestVO);
			} else {
				throw new UserRegistrationException(ExceptionCategory.EMAIL_ALREADY_EXISTS);
			}
		} catch (UserRegistrationException e) {
			throw e;
		}
		return created;
	}
	
	public boolean updateRegisteredUser(RegisteredUserVOAction requestVO) throws UserRegistrationException {
		boolean updated = false;
		RegisteredUserDAO registereduserDAO = new RegisteredUserDAO();
		try {
			updated = registereduserDAO.updateRegisteredUser(requestVO);
			
		} catch (UserRegistrationException e) {
			throw e;
		}
		return updated;
	}
}
