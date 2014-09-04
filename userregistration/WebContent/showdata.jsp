<%@page import="com.snailark.userregistration.model.RegisteredUserVOAction"%>
<%@page import="com.snailark.userregistration.*"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Get data</title>


</head>
<body>
	<div style="float: right; ">
		<a href="HomeController">Home</a>
	</div>
<div>
	<form method="get">
		<table>
			<tr>
				<th>
					Search
				</th>
			</tr>
			<tr>
				<td>
					Email
				</td>
				<td>
					<input type="text" name="emailtxt">
				</td>
			</tr>
			<tr>
				<td>
					First Name
				</td>
				<td>
					<input type="text" name="firstnametxt">
				</td>
			</tr>
			<tr>
				<td>
					Last Name
				</td>
				<td>
					<input type="text" name="lastnametxt">
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<button type="submit" name="submit"> Search</button>
				</td>
			</tr>
		</table>
	</form>
</div>
<div>
	<table align="center" border="1">
		<tr>
			<td>First Name</td>
			<td>Last Name</td>
			<td>Gender</td>
			<td>Date Of Birth</td>
			<td>E-Mail</td>
			<td>City</td>		
			<td>Phone Number</td>
		</tr>
		
		<%
					List<RegisteredUserVOAction> dbtable = (ArrayList<RegisteredUserVOAction>)request.getAttribute("dbtable"); 
					
					if(dbtable != null){
						for(RegisteredUserVOAction registeredUserVO : dbtable){
				%>
					<tr>
						<td><a href="UpdateController?id=<%=registeredUserVO.getUserId() %>"><%=registeredUserVO.getFirstName() %></a></td>
						<td><%=registeredUserVO.getLastName() %></td>
						<td><%=registeredUserVO.getGender() %></td>
						<td><%=registeredUserVO.getDateOfBirth() %></td>
						<td><%=registeredUserVO.getEmail() %></td>
						<td><%=registeredUserVO.getCity() %></td>		
						<td><%=registeredUserVO.getPhoneNumber() %></td>
					</tr>
					<%
				} 			
			}
			%>
	</table>
</div>
</body>
</html>