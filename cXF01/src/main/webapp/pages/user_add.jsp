<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
	<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
	<script type="text/javascript">
	$(function(){
		$('.btn1').click(function(){
		var jsonVal={"name":$("#user_add_nameVal").val()};//JSON.stringify(jsonVal)
		if(null==$("#user_add_nameVal").val() || ''==$("#user_add_nameVal").val())  return;
				$.ajax({
					url:'addOneUser.action',
					type:'POST',
					data:JSON.stringify({"name":$("#user_add_nameVal").val()}),
					contentType:"application/json",
					success:function(data){
						console.log("success");
						if(data.code=='8'){
						var locate=location.href;
						var index=locate.lastIndexOf('/');
						locate=locate.substring(0, index)+'/list.jsp';
						console.log(locate);
							location.href=locate;
						} 
					},
					error:function(XMLHttpRequest, textStatus, errorThrown){
						console.log(XMLHttpRequest.status);
						console.log(textStatus);
						console.log(XMLHttpRequest.readyState);
						
					}
		
		});
		
		});
	
	})
	</script>
  </head>
  <body>
  <form>
  <input  type="name" id="user_add_nameVal" name="name">
  <br>
  <button type="button" class="btn btn1">提交</button>
  
  
  </form>
  </body>
  </html>