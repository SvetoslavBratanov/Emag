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
			<h1>Welcome, please login</h1>
		</c:if>
		<c:if test="${ requestScope.error != null }">
			<h1 style="color: red">Sorry, username or password missmatch. Reason: ${requestScope.error }</h1>
		</c:if>
		<form action="login" method="post">
			Username<input type="text" name="user"><br>
			Password<input type="password" name="pass"><br>
			<input type="submit" value="Login"><br>
		</form>
		Don`t have an account yet? Please register <a href="register.jsp">here</a>.
	</body>
</html>