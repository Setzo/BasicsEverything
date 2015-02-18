<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<jsp:useBean id="user0" class="beans.User" scope="page"></jsp:useBean>

<jsp:setProperty property="password" name="user0" param="p"/>

<jsp:setProperty property="email" name="user0" param="e"/>

<%= user0.getPassword() %> <br>
<%= user0.getEmail() %>

<br><br>

<jsp:useBean id="user1" class="beans.User" scope="page"></jsp:useBean>

<jsp:setProperty property="*" name="user1" />

<%= user1.getPassword() %> <br>
<%= user1.getEmail() %>

</body>
</html>
