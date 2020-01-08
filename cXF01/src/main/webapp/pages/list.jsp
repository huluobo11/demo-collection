<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML >
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'list.jsp' starting page</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
<script type="text/javascript">
	$(function() {
		$.ajax({
			url : 'list.action',
			type : 'POST',
			dataType : 'json',
			success : function(data) {
					var list = data.userList;
					console.log(list);
					var str = '';
					for (var i = 0; i < list.length; i++) {
						str += list[i].id + '--------' + list[i].name + "<a href='deleteOneUser.action?id="+list[i].id+"'>删除</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href='getOneUser.action?id="+list[i].id+"'>修改</a></br>"
					}
					$('#user_pageListDiv')	.html(str);
			}
		})

		$('._btn').click(function() {
			$.ajax({
				url : 'getOneById',
				type : 'POST',
				data : '{"id":1}',
				dataType : 'json',
				contentType : "application/json",
				success : function(data) {
					$('#content').html("");
					$('#content').html(data.id + "+++++++++++" + data.name);
				}
			});

		});

		$('#query_user').click(function() {
			var jsonVal = {
				"name" : $("#nameVal").val()
			};
			$.ajax({
				url : 'queryUserByNameForPage.action',
				type : 'POST',
				data : JSON.stringify(jsonVal),
				dataType : 'json',
				contentType : "application/json",
				success : function(data) {
					console.log(data);
					var list = data.pageList;
					console.log(list);
					var str = '';
					for (var i = 0; i < list.length; i++) {
						str += list[i].id + "--------" + list[i].name + "</br>"
					}
					$('#content').html("");
					$('#content').html(str);
					console.log(str);
				}

			});

		});
	})
</script>
</head>

<body>
	<button class="_btn" type="button">执行ajax</button>
	<a href="pages/user_add.jsp">新增</a>
	<br />
	<form id="user_pageForm">
		<input id="nameVal" type="text" name="name">
		<button id="query_user" type="button">查询</button>
	</form>
	<div id="user_pageListDiv"></div>
	<h3>######################</h3>
	<div id="content"></div>
</body>
</html>
