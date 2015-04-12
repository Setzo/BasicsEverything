
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<c:import url="header.jsp">
<c:param name="title" value="Header"></c:param>
</c:import>

<sql:setDataSource var="ds" dataSource="jdbc/testdatabase" />

<sql:query dataSource="${ds}" sql="select * from images limit 10" var="result"/>

<c:set var="wid" value ="5"/>

<table>
			<tr>
	<c:forEach var="image" items="${result.rows}" varStatus="row">
	
		<c:if test='${row.index % wid == 0}'>
			<tr>
		</c:if>
	
		<c:set scope="page" var="img" value="${image.stem}.${image.image_extension}"></c:set>
		<td><img width="200" src="${pageContext.request.contextPath}/pics/${img}"/></td>
		
		<c:if test='${row.index + 1 % wid == 0}'>
			</tr>
		</c:if>
		
	</c:forEach>
</table>

<c:import url="mid.jsp"></c:import>

<c:import url="footer.jsp"></c:import>
