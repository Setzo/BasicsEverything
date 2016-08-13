<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Hello JSTL</title>
</head>
<body>
	
	<%-- JSTL sysout --%>
	<c:out value="Hello World!"></c:out>

	<jsp:useBean id="test" class="beans.TestBean" scope="page"></jsp:useBean>
			
	<%-- JSTL get beans --%>
	<p>
		Value of getInfo() method for beans.TestBean test:
		<c:out value="${test.info}" />
	</p>
	
	<%-- JSTL get url params --%>
	<p>
		v: <c:out value="${param.v}"/>
	</p>
	
	<%-- JSTL if --%>
	<p>
		<c:if test='${param.v == "video"}'>
			v == video
		</c:if>
		<c:if test='${param.v != "video"}'>
			v != video
		</c:if>
	</p>

</body>
</html>
