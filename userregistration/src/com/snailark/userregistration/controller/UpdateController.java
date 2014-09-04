package com.snailark.userregistration.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
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
import com.snailark.userregistration.util.ValidationHelper;

/**
 * Servlet implementation class UpdateController
 */
@WebServlet("/UpdateController")
public class UpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @throws IOException 
	 * @throws ServletException 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		RegisteredUserVOAction registeredUserVO = new RegisteredUserVOAction();
		RequestDispatcher requestdispatcher = null;
		registeredUserVO.setUserId(Integer.parseInt(request.getParameter("id")));
		UserregistrationFacade userRegistrationFacade = new UserregistrationFacade();

			try {
				registeredUserVO = userRegistrationFacade.showUser(registeredUserVO);
				request.setAttribute("registeredUserVO",registeredUserVO);
				requestdispatcher = request.getRequestDispatcher("/update_page.jsp");
				requestdispatcher.forward(request, response);
			} catch (UserRegistrationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		String forwardPath = "/update_page.jsp";
		
		List<String> errorMessages = new ArrayList<String>();
		request.setAttribute("errorMessages", errorMessages);
		RequestDispatcher requestdispatcher = null;
		UserregistrationFacade userRegistrationFacade = new UserregistrationFacade();
		RegisteredUserVOAction registeredUserVO = new RegisteredUserVOAction();
		registeredUserVO.setUserId(Integer.parseInt(request.getParameter("id")));
		boolean errorsFound = createRegisteredUserVO(request, registeredUserVO, errorMessages);
		if(!errorsFound) {
			try {
				boolean updated = userRegistrationFacade.updateRegisteredUser(registeredUserVO);
				errorMessages.add(!updated ? "User updated Successfully" : "Error while Update User");
			} catch (UserRegistrationException e) {
				errorMessages.add(e.getExceptionCategory().getMessage());
				forwardPath = "/update_page.jsp";
			}
		} else {
			forwardPath = "/update_page.jsp";
		}
		request.setAttribute("registeredUserVO", registeredUserVO);
		requestdispatcher = request.getRequestDispatcher(forwardPath);
		requestdispatcher.forward(request, response);
	}

	private boolean createRegisteredUserVO(HttpServletRequest request, RegisteredUserVOAction registeredUserVO,  List<String> errorMessages) {
		boolean foundErrors = false;
		if(ValidationHelper.nameValidation(request.getParameter("firstnametxt"))){
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
		
			registeredUserVO.setGender((request.getParameter("gender")));			
		
			Calendar date = Calendar.getInstance();
			date.clear();
			date.set(Calendar.YEAR, Integer.parseInt(request.getParameter("yy")));
			date.set(Calendar.MONTH, Integer.parseInt(request.getParameter("mm")));
			date.set(Calendar.DATE, Integer.parseInt(request.getParameter("dd")));
			registeredUserVO.setDateOfBirth(date.getTime());		
				
			registeredUserVO.setEmail(request.getParameter("emailtxt"));
			
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
		}
		return foundErrors;
	}


}


