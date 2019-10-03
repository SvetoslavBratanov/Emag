<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
	<body>
		<c:if test="${ sessionScope.user == null }">
			<c:redirect url="login.jsp"></c:redirect>
		</c:if>
		<jsp:include page="header.jsp"></jsp:include>
	
		<h2>Your previous orders:</h2>	
		
		<a href="sortOrders?sort=desc"><button>Newest to oldest</button></a>
		<a href="sortOrders?sort=asc"><button>Oldest to newest</button></a>
		
		<c:forEach items="${ sessionScope.user.orders }" var="order">
			<h4>${ order.datetime }</h4>
			<table border="1">
			<c:forEach items="${ order.products }" var="productEntry">
				<tr>
					<td>${productEntry.key }</td>
					<td>${productEntry.value }</td>
				</tr>
			</c:forEach>
			</table>
			<hr>
		</c:forEach>
		<jsp:include page="footer.jsp"></jsp:include>
	</body>
</html>