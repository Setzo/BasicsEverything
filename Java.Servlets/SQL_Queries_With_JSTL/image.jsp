
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<c:import url="header.jsp">
<c:param name="title" value="View Image"></c:param>
</c:import>

<sql:setDataSource var="ds" dataSource="jdbc/testdatabase" />

<sql:query dataSource="${ds}" sql="select * from images where id=?" var="result">
<sql:param>${param.img}</sql:param>
</sql:query>

<c:set var="wid" value ="5"/>
	
<c:set scope="page" var="image" value="${result.rows[0]}"></c:set>
<c:set scope="page" var="img" value="${image.stem}.${image.image_extension}"></c:set>
	
<img width="500" src="${pageContext.request.contextPath}/pics/${img}"/>

<c:import url="footer.jsp"></c:import>
