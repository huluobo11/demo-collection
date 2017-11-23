<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<script type="text/javascript">
	/* $(function() {
		$(".tds").css("style", "width:30px");
		$.each($(".tds"), function(i, item) {
		if(item.text().length>10){
			var td+i=$(item).text();
			$(item).onmouseover(function(){
				$(this).toggle();
			});
		
		}
		
		
		});
	}) */
	$(function() {
		if (true) {
			$("span").html("<font id=\"error\" color=\"red\">用户名已经被注册</font>");
		} else {
			$("span").html("");
		}
	})
</script>
</head>

<body>
	<p style="font-size: 20px">用户昵称:</p>
	<input type="text" name="username" id="username" />
	<span></span>
	
</body>
</html>