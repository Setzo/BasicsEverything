
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:import url="header.jsp">
<c:param name="title" value="View Image"></c:param>
</c:import>

<sql:setDataSource var="ds" dataSource="jdbc/testdatabase" />

<sql:query dataSource="${ds}" sql="select * from images where id=?" var="result">
	<sql:param>${param.img}</sql:param>
</sql:query>

<center>

	<c:set scope="page" var="img" value="${result.rows[0].stem}.${result.rows[0].image_extension}"></c:set>
		
	<h2><c:out value="${fn:toUpperCase(result.rows[0].stem)}"/></h2>
	
	<img width="500" src="${pageContext.request.contextPath}/pics/${img}"/>

</center>

<c:import url="footer.jsp"></c:import>
