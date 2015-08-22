<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript" src="script/jquery-1.7.2.js"></script>
<%@ include file="/comments/condition.jsp" %>
</head>
<body>

	<center>
	
		BookID:${book.id }
		<br><br>
		Author:${book.author }
		<br><br>
		Title:${book.title }
		<br><br>
		Price:${book.price }
		<br><br>
		PublishingDate:${book.publishingDate }
		<br><br>
		SalesAmount:${book.salesAmount }
		<br><br>
		<br><br>
		
	<a href="bookServlet?method=getBooks&pageNo=${param.pageNo }">继续购物</a>
	</center>


</body>
</html>