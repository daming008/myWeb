<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<center>
		<br><br>
		您一共买了${sessionScope.ShoppingCart.bookNumber }本书。<br>
		您应付金额：￥${sessionScope.ShoppingCart.totalMoney }
		<br>
		<c:if test="${requestScope.errors != null }">
			<font color="red">${requestScope.errors }</font>
			<br>
		</c:if>
		<form action="bookServlet?method=cash" method="post">
			<table cellpadding="10">
				<tr>
					<th>信用卡姓名：</th>
					<th><input type="text" name="cardname"/></th>
				</tr>
				<tr>
					<th>信用卡账号：</th>
					<th><input type="text" name="cardaccount"/></th>
				</tr>
				<tr >
					<th colspan="2"><input type="submit" value = "Submit"></th>
				</tr>
			</table>
		</form>
	</center>
</body>
</html>