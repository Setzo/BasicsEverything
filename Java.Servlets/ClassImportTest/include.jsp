<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<!-- static include -->
<%@ include file="copy.txt" %>

<br><br>

<!-- dynamic include -->
<jsp:include page="updates.txt"/>

<br><br>

<!-- static include always, when need to access variables in file -->
<%@ include file="variables.jsp" %>
<%= name %>

<br><br>

<!-- always use dynamic include when u dont know which file you will be using -->
<%if(request.getParameter("id") == null) { %>
	<jsp:include page="updates.txt"/>
<%} else { %>
	<jsp:include page="copy.txt"/>
<%} %>

</body>
</html>
