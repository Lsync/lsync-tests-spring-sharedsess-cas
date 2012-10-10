<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="false" contentType="text/html" pageEncoding="UTF-8"%>
<html>
<head>
<title>Home</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>
	<h1>${appName}</h1>
	<h2>${page}</h2>

	<div>The time on the server is ${serverTime}.</div>
	<div>
		You are: <strong>${userLogin}</strong>
	</div>
	<div>isUser: ${isUser}</div>
	<div>isAdmin: ${isAdmin}</div>
	<p>
		<a href="http://localhost.com:8080/app01/">app01</a> / <a href="http://localhost.com:8080/app02/">app02</a>
	</p>
	<p>
		<a href="<c:url value='/' />">Home</a> / <a href="<c:url value='/page1' />">Page1</a> / <a
			href="<c:url value='/page2' />">Page2</a> / <a href="<c:url value='/safe' />">SafePage</a>
	</p>
	<p>
		<a href="<c:url value='/login' />">Login</a> / <a href="<c:url value='/j_spring_security_logout' />">Logout</a>
	</p>
</body>
</html>
