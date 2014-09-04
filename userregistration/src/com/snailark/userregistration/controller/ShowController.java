package com.snailark.userregistration.controller;

import java.io.IOException;
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
 * Servlet implementation class ShowController
 */
@WebServlet("/ShowController")
public class ShowController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * 
	 * @throws IOException 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
	
		RequestDispatcher requestdispatcher = null;
		List<RegisteredUserVOAction> dbusers;
		UserregistrationFacade userregistrationFacade = null;		
	
			try {
				RegisteredUserVOAction registeredUserVO = new RegisteredUserVOAction();
				registeredUserVO.setEmail(request.getParameter("emailtxt"));
				registeredUserVO.setFirstName(request.getParameter("firstnametxt"));
				registeredUserVO.setLastName(request.getParameter("lastnametxt"));
				userregistrationFacade = new UserregistrationFacade();
				dbusers = userregistrationFacade.showAllUser(registeredUserVO);
				request.setAttribute("dbtable",dbusers);
				requestdispatcher = request.getRequestDispatcher("/showdata.jsp");
				requestdispatcher.forward(request, response);
			} catch (UserRegistrationException e) {
				e.printStackTrace();
			} catch (IOException e) {
				throw e;
			}
	
		
		
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
