<%@page import="model.db.ProductDao"%>
<%@page import="model.Product"%>
<%@page import="java.util.HashSet"%>
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
		
		<table border="1">
			<tr>
				<th>Id</th>
				<th>Name</th>
				<th>Quantity</th>
				<th>Price</th>
			</tr>
			<c:forEach items="${applicationScope.products}" var="product">
				<tr>
					<td>${ product.id }</td>
					<td><c:out value="${ product.name }"></c:out></td>
					<td><c:out value="${ product.quantity } лв."></c:out></td>
					<td><c:out value="${ product.price } бр."></c:out></td>
				</tr>
			</c:forEach>
		</table>
		
		
		<jsp:include page="footer.jsp"></jsp:include>
	</body>
</html>