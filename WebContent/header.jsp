<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<link rel="stylesheet" type="text/css" href="styles.css">
	</head>
	<body>
		<div class="header">
			<form class="navi" action="about.jsp">
				<input type="submit" value="About Us">
			</form>
			<form class="navi" action="contact.jsp">
				<input type="submit" value="Contacts">
			</form>
			<form class="navi" action="main.jsp">
				<input type="submit" value="Products on sale">
			</form>
			<form class="navi" action="orders.jsp">
				<input type="submit" value="View My orders">
			</form>
			<form class="navi" action="logout" method="post">
				<input type="submit" value="Logout">
			</form>
			<img id="avatar" src="avatar">
			<h3 class="welcome">Welcome, ${ sessionScope.user.username }</h3>
		</div>
	</body>
</html>