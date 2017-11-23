<%@  page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'index.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript">
	function getOs() {
		var OsObject = "";
		if (isIE = navigator.userAgent.indexOf("MSIE") != -1) {
			return "MSIE";
		}
		if (isFirefox = navigator.userAgent.indexOf("Firefox") != -1) {
			return "Firefox";
		}
		if (isChrome = navigator.userAgent.indexOf("Chrome") != -1) {
			return "Chrome";
		}
		if (isSafari = navigator.userAgent.indexOf("Safari") != -1) {
			return "Safari";
		}
		if (isOpera = navigator.userAgent.indexOf("Opera") != -1) {
			return "Opera";
		}

	}
	$(function() {
		var result = getOs();
		if (result == "MSIE") {
			//是ie
			$("#input的id").on('onpropertychange', function() {

			});

		} else {
			//不是ie

			$("#input的id").on('oninput', function() {

			});

		}

	})
</script>
</head>

<body>



</body>
</html>
