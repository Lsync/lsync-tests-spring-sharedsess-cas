<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page session="false" contentType="text/html" pageEncoding="UTF-8"%>
<html>
<head>
<title>CAS-Logout</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>
	<h1>CAS-Logout</h1>
	<h2>You are about logout from CAS server</h2>

	<p>
		If your browser doesn't support redirections, please press this link:
	</p>
	<p>
		<a href="<c:url value='/j_spring_cas_security_logout' />">CAS logout</a>
	</p>
</body>
</html>
<% response.sendRedirect("j_spring_cas_security_logout"); %>
