<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h4>listPage</h4>
	Welcome:
	<shiro:principal></shiro:principal>
	<br>
	<shiro:hasRole name="admin">
		<a href="admin.jsp">admin page</a>
	</shiro:hasRole>
	<br>
	<shiro:hasRole name="user">
		<a href="user.jsp">user page</a>
	</shiro:hasRole>
	<br>
	<a href="shiro/testShiroAnnotation">TestShiroAnnotation</a>
	<br />
	<a href="shiro/logout">logout</a>
	<br>
	<br>
</body>
</html>