package com.snailark.userregistration.controller;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.snailark.userregistration.exception.UserRegistrationException;
import com.snailark.userregistration.facade.UserregistrationFacade;
import com.snailark.userregistration.model.RegisteredUserVOAction;

/**
 * Servlet implementation class RegistrationController
 */
@WebServlet("/RegistrationController")
public class RegistrationController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegistrationController() {
		super();

	}

	
	
	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String forwardPath = "/update_page.jsp";
		
		List<String> errorMessages = new ArrayList<String>();
		request.setAttribute("errorMessages", errorMessages);
		RequestDispatcher requestdispatcher = null;
		UserregistrationFacade userRegistrationFacade = new UserregistrationFacade();
		RegisteredUserVOAction registeredUserVO = new RegisteredUserVOAction();
		
		boolean errorsFound = createRegisteredUserVO(request, registeredUserVO, errorMessages);
		if(!errorsFound) {
			try {
				boolean created = userRegistrationFacade.createRegisteredUser(registeredUserVO);
				errorMessages.add(!created ? "User Created Successfully" : "Error while creating User");
			} catch (UserRegistrationException e) {
				errorMessages.add(e.getExceptionCategory().getMessage());
				forwardPath = "/registration.jsp";
			}
		} else {
			forwardPath = "/registration.jsp";
		}
		request.setAttribute("registeredUserVO", registeredUserVO);
		requestdispatcher = request.getRequestDispatcher(forwardPath);
		requestdispatcher.forward(request, response);
	}

	private boolean createRegisteredUserVO(HttpServletRequest request, RegisteredUserVOAction registeredUserVO,  List<String> errorMessages) {
		boolean foundErrors = false;
		/*if(ValidationHelper.nameValidation(request.getParameter("firstnametxt"))){
			registeredUserVO.setFirstName(request.getParameter("firstnametxt"));
		} else {
			foundErrors = true;
			errorMessages.add("Invalid Firstname field");
		}
			
		if(ValidationHelper.nameValidation(request.getParameter("lastnametxt"))){			
			registeredUserVO.setLastName(request.getParameter("lastnametxt"));
		} else {
			foundErrors = true;
			errorMessages.add("Invalid Lastname field");
		}
		
		if(request.getParameter("gender") != null){
			registeredUserVO.setGender((request.getParameter("gender")));			
		} else {
			foundErrors = true;
			errorMessages.add("Please select Gender");
		}
		
		if(ValidationHelper.dateOfBirthValidation(request.getParameter("dd"), request.getParameter("mm"), request.getParameter("yy"))){
		// ---- date of birth date setting
			Calendar date = Calendar.getInstance();
			date.clear();
			date.set(Calendar.YEAR, Integer.parseInt(request.getParameter("yy")));
			date.set(Calendar.MONTH, Integer.parseInt(request.getParameter("mm")));
			date.set(Calendar.DATE, Integer.parseInt(request.getParameter("dd")));
			registeredUserVO.setDateOfBirth(date.getTime());
			registeredUserVO.setDate(request.getParameter("dd"));
			registeredUserVO.setMonth(request.getParameter("mm"));
			registeredUserVO.setYear(request.getParameter("yy"));
		} else {
			foundErrors = true;
			errorMessages.add("Please select valid Date Of Birth.");
		}
		
		if (EmailValidator.validateEmail(request.getParameter("emailtxt")) ){			
			registeredUserVO.setEmail(request.getParameter("emailtxt"));
		} else {
			errorMessages.add(ExceptionCategory.INVALID_EMAIL.getMessage());
		}
		
		if(ValidationHelper.nameValidation(request.getParameter("citytxt"))){
			registeredUserVO.setCity(request.getParameter("citytxt"));
		} else {
			foundErrors = true;
			errorMessages.add("Please enter valid city.");
		}
		if(ValidationHelper.phoneNumberValidation(request.getParameter("phonetxt"))){
			registeredUserVO.setPhoneNumber(Long.parseLong(request.getParameter("phonetxt")));
		} else {
			foundErrors = true;
			errorMessages.add("Please enter valid phone number.");
		}*/
		
		Field fld[] = RegisteredUserVOAction.class.getDeclaredFields();
		  for (Field field : fld) 
		    {
		        System.out.println(field.getName());   //gives the names of the fields
		    }
		Enumeration<String> enumeration = request.getParameterNames();  
		while(enumeration.hasMoreElements()){
			for(int i = 0; i < fld.length; i++){
				boolean varName = false;
				if(enumeration.nextElement().equals(fld[i])){
					varName = true;
				}
				if(!varName){
					foundErrors = true;
				}
					
			}
		}  
		
		return foundErrors;
	
	

	}
	

}
