<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Gimme objects</title>
</head>
<body>

	<c:out value="${user1.name}"/> <br>
	<c:out value="${user2.name}"/> <br>
	<c:out value="${user3.name}"/> <br>
	
	<c:out value="${sessionScope.user2.name}"/> <br>
	
	<%--  ACCESSING SERVLET THINGS
	<c:out value="${pageContext.}"/> <br>
	--%>
	
	<c:out value='${map1["key"]}'/> <br>
	<c:out value='${map1["key0"]}'/> <br>
	<c:out value='${link}'/> <br>
	
	${link} <br>
		
	<table style="border: 1px solid blue;">
		<c:forEach var="usr" items="${list1}">
			<tr><td><c:out value='${usr.name} + ${usr.id}'/> </td></tr>
		</c:forEach>
	</table>
	
</body>
</html>
