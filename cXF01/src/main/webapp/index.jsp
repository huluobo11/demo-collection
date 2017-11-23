<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
</head>
<body>
	<h2>Hello World!</h2>
<ul>
<li><a href="pages/list.jsp">list</a></li>
<li><a href="list.jsp">list</a></li>
</ul>
</body>
</html>
