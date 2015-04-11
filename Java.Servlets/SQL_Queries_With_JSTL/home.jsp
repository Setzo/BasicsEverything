
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<c:import url="header.jsp">
<c:param name="title" value="Header"></c:param>
</c:import>

<sql:setDataSource var="ds" dataSource="jdbc/testdatabase" />

<sql:query dataSource="${ds}" sql="select * from images limit 10" var="result"/>

<c:forEach var="image" items="${result.rows}">

	<c:set scope="page" var="img" value="${image.stem}.${image.image_extension}"></c:set>
	<p><img src="${pageContext.request.contextPath}/pics/${img}"/></p>
	
</c:forEach>

<c:import url="mid.jsp"></c:import>

<c:import url="footer.jsp"></c:import>
