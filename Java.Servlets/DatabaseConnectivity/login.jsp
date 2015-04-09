<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Login</title>

<style type="text/css">
#content {
	position: relative;
}

#login {
	position: relative;
	top: 100px;
}

.ar {text-align right;
	
}

table {
	border: 1px solid gray;
	padding: 20px;
	background-color: #CCCCFF;
}

.lr {
	font-size: 16px;
	font-weight: bold;
	font-color: red;
}
</style>

</head>


<body>

	<center>

		<div id="login">

			<form method="post"
				action="<%=response.encodeURL(request.getContextPath())
					+ "/Controller?action=dologin"%>">

				<input type="hidden" name="action" value="dologin" />

				<table>

					<tr>
						<td class="ar">Email address:</td>
						<td><input type="text" name="email"
							value="<%=request.getAttribute("email") == null ? "" : request
					.getAttribute("email")%>" /></td>
					</tr>
					<tr>
						<td class="ar">Password:</td>
						<td><input type="password" name="password"
							value="<%=request.getAttribute("password") == null ? "" : request
					.getAttribute("password")%>" /></td>
					</tr>
					<tr>
						<td class="ar" colspan="2"><input type="submit"
							value="Log in" /></td>
					</tr>

				</table>

				<p class="lr"><%=request.getAttribute("message") == null ? "" : request
					.getAttribute("message")%></p>

			</form>

		</div>

	</center>

</body>

</html>
