<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Insert title here</title>
<script type="text/javascript" src="script/jquery-1.7.2.js"></script>
<script type="text/javascript" src="script/jquery.base64.js"></script>
<script type="text/javascript">

$(function(){
// 	for(var i=0;i<10;i++){
// 	var temp;
// 	var userid = $.base64.encode('ldm0317011');
// 	$(".userID").html(temp+=userid+",");
		
// 	}

	var str ="ldm01,ldm02,ldm03,ldm04";
	var arrays = str.split(",");
	var userNames="";
	for(var i=0;i<arrays.length;i++){
		var userName = $.base64.encode(arrays[i]);
		
		
		userNames += userName+",";
		
	}
	$(".userID").html(userNames);
		var userName1 = $.base64.decode('bGRtOmxpZGVtaW5n');
		alert(userName1);
	
	
})

</script>
</head>
<body>

	<div class="userID" >
	</div>


</body>
</html>