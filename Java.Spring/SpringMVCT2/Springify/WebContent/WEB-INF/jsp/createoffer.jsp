<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link href="${pageContext.request.contextPath}/res/css/create-offer-form.css" rel="stylesheet" type="text/css">

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><c:out value="${title}" /></title>
</head>
<body>

	<s:form method="post" 
		action="${pageContext.request.contextPath}/docreate" commandName="offer">
		
		<table class="ftab">
			<tr><td class="label">Name: </td><td><s:input class="cntrl" type="text" path="name" name="name" /><br><s:errors path="name" cssClass="error"></s:errors></td></tr>
			<tr><td class="label">Email: </td><td><s:input class="cntrl" type="text" path="email" name="email" /><br><s:errors path="email" cssClass="error"></s:errors></td></tr>
			<tr><td class="label">Offer: </td><td><s:textarea class="cntrl" rows="10" cols="100" path="text" name="text"></s:textarea><br><s:errors path="text" cssClass="error"></s:errors></td></tr>
			<tr><td class="label"></td><td><input class="cntrl" type="submit" value="Submit" /></td></tr>
		</table>
		
	</s:form>

</body>
</html>