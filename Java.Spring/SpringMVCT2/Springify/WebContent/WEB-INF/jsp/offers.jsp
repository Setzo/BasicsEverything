<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><c:out value="${title}" /></title>
</head>
<body>

	<strong>
		<c:out value="${text}" />
	</strong>

	<ul>
		<c:forEach var="row" items="${offerList}">
			<li>ID: <c:out value="${row.id}"/></li>
			<li>Name: <c:out value="${row.name}"/></li>
			<li>Email: <c:out value="${row.email}"/></li>
			<li>Text: <c:out value="${row.text}"/></li>

			<br>
		</c:forEach>
	</ul>
	
	<a href="${pageContext.request.contextPath}/">Click to return to homepage.</a>

</body>
</html>