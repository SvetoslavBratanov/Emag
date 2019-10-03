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
		<c:if test="${ requestScope.error == null }">
			<h1>Welcome, please register an account</h1>
		</c:if>
		<c:if test="${ requestScope.error != null }">
			<h1 style="color: red">Sorry, registration unsuccessfull. Reason: ${requestScope.error }</h1>
		</c:if>
		<form action="register" method="post" enctype="multipart/form-data">
			Username<input type="text" name="user"><br>
			Password<input type="password" name="pass"><br>
			Confirm password<input type="password" name="pass2"><br>
			Email<input type="email" name="email"><br>
			Avatar<input type="file" name="avatar"><br>
			<input type="submit" value="Register"><br>
		</form>
		Already have an account? Please login <a href="login.jsp">here</a>.
	</body>
</html>