<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link href="${pageContext.request.contextPath}/res/css/create-offer-form.css" rel="stylesheet" type="text/css">

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><c:out value="${title}" /></title>
</head>
<body>

	<form method="post" 
		action="${pageContext.request.contextPath}/docreate">
		
		<table class="ftab">
			<tr><td class="label">Name: </td><td><input class="cntrl" type="text" name="name" /></td></tr>
			<tr><td class="label">Email: </td><td><input class="cntrl" type="text" name="email" /></td></tr>
			<tr><td class="label">Offer: </td><td><textarea class="cntrl" rows="10" cols="100" name="text"></textarea></td></tr>
			<tr><td class="label"></td><td><input class="cntrl" type="submit" value="Submit" /></td></tr>
		</table>
		
	</form>

</body>
</html>