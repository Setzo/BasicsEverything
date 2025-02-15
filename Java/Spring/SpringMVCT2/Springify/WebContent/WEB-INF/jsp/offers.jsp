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

	<strong> <c:out value="${text}" />
	</strong>

	<table>

		<tr>
			<td>ID</td>
			<td>Name</td>
			<td>Email</td>
			<td>Offer</td>
		</tr>

		<c:forEach var="row" items="${offerList}">
			<tr>
				<td><c:out value="${row.id}" /></td>
				<td><c:out value="${row.name}" /></td>
				<td><c:out value="${row.email}" /></td>
				<td><c:out value="${row.text}" /></td>
			</tr>
		</c:forEach>

	</table>

	<p><a href="${pageContext.request.contextPath}/">Click to return to homepage.</a></p>

</body>
</html>