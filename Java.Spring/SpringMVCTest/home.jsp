<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><c:out value="${title}" /></title>
</head>
<body>

	<c:out value="${text}" />

	<sql:query var="rs" dataSource="jdbc/springify">
		select * from offers
	</sql:query>

	<ul>
		<c:forEach var="row" items="${rs.rows}">
			<li>ID: <c:out value="${row.id}"/></li>
			<li>Name: <c:out value="${row.name}"/></li>
			<li>Email: <c:out value="${row.email}"/></li>
			<li>Text: <c:out value="${row.text}"/></li>

			<br>
		</c:forEach>
	</ul>

</body>
</html>
