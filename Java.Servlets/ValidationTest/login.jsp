<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body>

	<form action="/MVCForm/Controller" method="post">
	
		<input type="hidden" name="action" value="dologin" />
		Email: <input type="text" name="email" value="<%= request.getAttribute("email") %>" /> <br>
		Password: <input type="text" name="password" value="<%= request.getAttribute("password") %>" /> <br>
		<input type="submit" value="Submit" />
		
	</form>
	
	<strong><%= request.getAttribute("message") %></strong>

</body>
</html>
