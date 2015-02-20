<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<jsp:useBean id="currentUser" scope="session" class="beans.User"></jsp:useBean>
	
	<jsp:setProperty property="*" name="currentUser"/>



	<%
		if (request.getParameter("act") != null) {
			if (currentUser.isValid()) {

				request.getRequestDispatcher("/Controller").forward(request, response);
			}
		}
	%>

	<form action="/Forms/selfvalidatingform.jsp" method="post">
	
		<input type="hidden" name="act" value="done" />
		<input type="text" name="email" value='<jsp:getProperty property="email" name="currentUser"/>' />	<br>
		<input type="text" name="password" value="<%= currentUser.getPassword() %>"/><br>
		
		<input type="submit" value="ok"/>
	</form>

</body>
</html>
