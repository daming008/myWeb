<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%-- <%@ include file="/comments/condition.jsp" %> --%>
<script type="text/javascript" src="script/jquery-1.7.2.js"></script>
<script type="text/javascript">
	$(function(){
		
		$("a").click(function(){
			
		 	var serializeVal = $(":hidden").serialize();
		 	var href = this.href + "&"+ serializeVal;
		 	window.location.href = href;
		 	return false;
		})
		
		$("#pageNo").change(function(){
			var val = $(this).val();
			val = $.trim(val);
			var reg = /^\d+$/g;
			if(!reg.test(val)){
				alert("输入的不合法");
				$(this).val("");
				return;
			}
			
			var pageNo = parseInt(val);
			
			if(pageNo < 1 || pageNo > parseInt("${bookPage.totalPageNumber }") ){
				alert("页数不合法");
				return ;
			}
			var href = "bookServlet?method=getBooks&pageNo="+ pageNo +"&" + $(":hidden").serialize();
			window.location.href = href;
		})
	})
</script>
</head>
<body>
<input type="hidden" name="minPrice" value="${param.minPrice }"/>
<input type="hidden" name="maxPrice" value="${param.maxPrice }"/>
<center>
	<c:if test="${!empty param.title }">
	您已经${param.title }将加入购物车中
	</c:if>
	<br><br>	
	<c:if test="${!empty sessionScope.ShoppingCart.books }">
	您的购物车中有${sessionScope.ShoppingCart.bookNumber }件商品，<a href="bookServlet?method=forward&page=cart&pageNo=${bookPage.pageNo }">查看购物车</a>
	</c:if>
	<br><br>
	<form action="bookServlet?method=getBooks" method="post">
		price:<input type="text" size="1" name="minPrice"/> -
			<input type="text" size="1" name="maxPrice"/>
		
		<input type="submit" name="Submit" />
	</form>
	<br><br>
	<table>
		<c:forEach items="${bookPage.list }" var="book">
			<tr>
				<td>
					<a href="bookServlet?method=getBook&pageNo=${bookPage.pageNo }&id=${book.id }">${book.title }</a><br>
					${book.author }
				</td>
					
				<td>${book.price }</td>
				<td><a href="bookServlet?method=addToCart&pageNo=${bookPage.pageNo }&id=${book.id }&title=${book.title}">加入购物车</a></td>
			
			</tr>
		</c:forEach>
	</table>
	<br><br>
	共${bookPage.totalPageNumber }页
	&nbsp;
	当前第${bookPage.pageNo }页
	&nbsp;
	<c:if test="${bookPage.hasPrev }">
		<a href="bookServlet?method=getBooks&pageNo=1">首页</a>
		<a href="bookServlet?method=getBooks&pageNo=${bookPage.prevPage }">上一页</a>
	</c:if>
	
	<c:if test="${bookPage.hasNext }">
		<a href="bookServlet?method=getBooks&pageNo=${bookPage.nextPage }">下一页</a>
		<a href="bookServlet?method=getBooks&pageNo=${bookPage.totalPageNumber }">末页</a>
	</c:if>
	&nbsp;
	转到<input type="text" size="1" id="pageNo" />页
	
	</center>
</body>
</html>