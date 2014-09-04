<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@page import="com.snailark.userregistration.model.RegisteredUserVOAction"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="./css/styles.css" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update form</title>
</head>
<body>
	<div style="font-size:20; right: 0;  ">
		<a href="HomeController">Home</a>
	</div>
	<div
		style="font-size: 20; text-align: center; padding-top: 14px; font-size: xx-large;">
		Updation Form</div>
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
			<form method="post" action="UpdateController" class="form">
				<input type='hidden' name='id' value='<%=registeredUserVO.getUserId() %>'/>
				<ul>
					<li>First Name
						<% 
						if(registeredUserVO == null) {
						%>
							<input type="text" name="firstnametxt" class="textbox">
						<%
						} else {
						%>
							<input type="text" name="firstnametxt" class="textbox" value='<%=registeredUserVO.getFirstName()%>'>
						<%
						}
						%>													
					</li>

					<li>Last Name 
						<% 
						if(registeredUserVO == null) {
						%>
						<input type="text" name="lastnametxt" class="textbox">
						<%
						} else {
						%>
							<input type="text" name="lastnametxt" class="textbox" value='<%=registeredUserVO.getLastName()%>'>
						<%
						}
						%>
					</li>

					<li>Gender<br /> 
					 	<% 
					 	if(registeredUserVO != null) {
					 	
						 	if( "Male".equals(registeredUserVO.getGender())) { %>
								<input type="radio" name="gender" value="Male" class="radio" checked="checked">Male 
								<input type="radio"	name="gender" value="Female" class="radio" >Female
							<%} else { %>
								<input type="radio" name="gender" value="Male" class="radio" >Male
								<input type="radio"	name="gender" value="Female" class="radio" checked="checked">Female
							<%} 
							
						} else {%>
								<input type="radio" name="gender" value="Male" class="radio" >Male
								<input type="radio"	name="gender" value="Female" class="radio">Female
						
						<%} %>
					</li>

					<%
					String date = "DD", month = "MM" , year="YYYY";
					if(registeredUserVO != null) {
							Date dateOfBirth = registeredUserVO.getDateOfBirth();
							Calendar calendar = Calendar.getInstance();
							calendar.setTime(dateOfBirth);
					  		date = "" + calendar.get(Calendar.DATE);
					  		date = Integer.parseInt(date) < 10 ? "0" + date : date;
					  		month = "" + calendar.get(Calendar.MONTH);
					  		month = Integer.parseInt(month) < 10 ? "0" + month : month;
					  		year = "" + calendar.get(Calendar.YEAR);
					  		
					}
					%>
					<li>Date of Birth<br> <select name="dd" class="select">
					
							<option><%=date %></option>
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
							<option><%=month%> </option>
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
							<option><%=year%></option>
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
							<lable name="emailtxt" class="textbox"></lable>
							<%
						} else {
							%>
							<lable name="emailtxt" class="textbox" ><%=registeredUserVO.getEmail()%></lable>
							<%
						} 
						%>
						
					</li>


					<li>City 
						<%
						if(registeredUserVO == null) {
						%>
							<input type="text" name="citytxt" class="textbox">
						<%
						} else {
						%>
							<input type="text" name="citytxt" class="textbox" value='<%=registeredUserVO.getCity()%>' >
						<%
						} 
						%>
					</li>


					<li>Phone No. 
						<%
						if(registeredUserVO == null) {
						%>
							<input type="text" name="phonetxt" class="textbox">
						<%
						} else {
						%>
							<input type="text" name="phonetxt" class="textbox" value='<%=registeredUserVO.getPhoneNumber()%>'>
						<%
						} 
						%>
						
					</li>


					<li>
						<button type="submit" name="submit" class="button">Submit</button>
					</li>

				</ul>
			</form>
		</div>
	</div>
</body>
</html>