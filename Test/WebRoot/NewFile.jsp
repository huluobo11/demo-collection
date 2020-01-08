<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<script type="text/javascript">
	$(function() {
		$(".tds").css("style", "width:30px");
		$.each($(".tds"), function(i, item) {
		if(item.text().length>10){
			var td+i=$(item).text();
			$(item).onmouseover(function(){
				$(this).toggle();
			});
		
		}
		
		
		});
	})
</script>
</head>

<body>
	<table>
		<thead></thead>
		<tbody>
			<tr>
				<td class="tds">aefqwe</td>
				<td class="tds">123414242qweqrwerqr14243</td>
				<td class="tds">1234qweqweqweqweqweqweqweqweqweqweqwqwer351eqweqweqweqwerqw</td>
			</tr>
		</tbody>
	</table>
</body>
</html>