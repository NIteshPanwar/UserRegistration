<%@page import="com.snailark.userregistration.model.RegisteredUserVOAction"%>
<%@page import="java.util.List"%>
<%@page
	import="com.snailark.userregistration.exception.ExceptionCategory"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<link href="./css/styles.css" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration Form</title>
</head>
<body>

	<div
		style="font-size: 20; text-align: center; padding-top: 14px; font-size: xx-large;">
		Registration Form</div>
	<div id="bg">
		<%
			RegisteredUserVOAction registeredUserVO = (RegisteredUserVOAction)request.getAttribute("registeredUserVO");
			List<String> errorMessages = (List<String>) request.getAttribute("errorMessages");
			if (errorMessages != null) {
				for (String message : errorMessages) {
		%>
		<p style="text-color: red;"><%=message%></p>
		<%
				}
			}
		%>
		<div class="module">

			<form method="post" action="RegistrationController" class="form">
				<ul>
					<li>First Name
						<% 
						if(registeredUserVO == null) {
						%>
						<input type="text" name="firstName" class="textbox">
						<%
						} else {
						%>
							<input type="text" name="firstName" class="textbox" value='<%=registeredUserVO.getFirstName()%>'>
						<%
						}
						%>																	
					</li>

					<li>Last Name 
						<% 
						if(registeredUserVO == null) {
						%>
						<input type="text" name="lastName" class="textbox">
						<%
						} else {
						%>
							<input type="text" name="lastName" class="textbox" value='<%=registeredUserVO.getLastName()%>'>
						<%
						}
						%>
					</li>

					<li>Gender<br /> <input type="radio" name="gender"
						value="Male" class="radio">Male <input type="radio"
						name="gender" value="Female" class="radio">Female
					</li>

					<li>Date of Birth<br> <select name="dd" class="select">
							<option>DD</option>
							<%
								for (int i = 1; i <= 31; i++) {
							%>
							<option>
								<%
									out.print(i < 10 ? "0" + i : i);
								%>
							</option>
							<%
								}
							%>
					</select> <select name="mm" class="select">
							<option>MM</option>
							<%
								for (int i = 1; i <= 12; i++) {
							%>
							<option>
								<%
									out.print(i < 10 ? "0" + i : i);
								%>
							</option>
							<%
								}
							%>
					</select> <select name="yy" class="select">
							<option>YYYY</option>
							<%
								for (int i = 1980; i <= 2014; i++) {
							%>
							<option>
								<%
									out.print(i);
								%>
							</option>
							<%
								}
							%>
					</select>
					</li>


					<li>Email 
						<%
						if(registeredUserVO == null) {
						%>
						<input type="text" name="email" class="textbox">
						<%
						} else {
						%>
						<input type="text" name="email" class="textbox" value='<%=registeredUserVO.getEmail()%>'>
						<%
						} 
						%>
						
					</li>


					<li>City 
						<%
							if(registeredUserVO == null) {
						%>
						<input type="text" name="city" class="textbox">
						<%
						} else {
						%>
						<input type="text" name="city" class="textbox" value='<%=registeredUserVO.getCity()%>'>
						<%
						} 
						%>
					</li>


					<li>Phone No. 
						<%
							if(registeredUserVO == null) {
						%>
						<input type="text" name="phone" class="textbox">
						<%
						} else {
						%>
						<input type="text" name="phone" class="textbox" value='<%=registeredUserVO.getPhoneNumber()%>'>
						<%
						} 
						%>
						
					</li>


					<li>
						<button type="submit" name="submit" value="submit" class="button">Submit</button>
					</li>

				</ul>
			</form>
		</div>
	</div>
</body>
</html>