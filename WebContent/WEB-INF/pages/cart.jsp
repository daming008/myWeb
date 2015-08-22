<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript" src="script/jquery-1.7.2.js"></script>
<%@ include file="/comments/condition.jsp" %>
<script type="text/javascript">
	$(function(){
		
		
		$(".delete").click(function(){
			var $title =  $(this).parent().parent();
			var title = $.trim($title.find("td:first").text());
			var flag = confirm("确定要删除" + title + "的信息吗？");
			if(flag){
				return true;
			}
			return false;
		});
		
		$(":input").change(function(){
			
			var quantityVal = $.trim(this.value);
			var quantity = -1;
			var flag = false;
			var reg = /^\d+$/g;
			if(reg.test(quantityVal)){
				quantity = parseInt(quantityVal);
				if(quantity >= 0){
					flag = true;
				}
				
			}
			var $tr =  $(this).parent().parent();
			var title = $.trim($tr.find("td:first").text());
			
			if(quantity == 0){
				var flag2 = confirm("确定要删除" + title + "的信息吗？");
				if(flag2){
					var $a = $tr.find("td:last").find("a")[0];
					$a.onclick();
					return;
				}
				alert(href);
				return;
			}
			
			if(!flag){
					alert("输入的 不合法");
					$(this).val($(this).attr("class"));
					return;
				}
			
			
			var flag = confirm("确定要修改"+ title +"数量吗 ？");
			if(!flag){
				$(this).val($(this).attr("class"));
				return;
			}
			
			var url = "bookServlet";
			var idVal = $.trim(this.name);
			var args = {"method":"updateItemQuantity","id":idVal,"quantity":quantityVal,"time":new Date()};
			$.post(url,args,function(dataq){
				var bookNumber = dataq.bookNumber;
				var totalMoney = dataq.totalMoney;

				$("#totalMoney").text("总金额："+totalMoney);
				$("#bookNumber").text("您的购物车中有"+ bookNumber +"本书");
				
			},"JSON");
			
		})
		
	})
</script>
</head>
<body>
	
	<center>
	<dir id="bookNumber">您的购物车中有${sessionScope.ShoppingCart.bookNumber }本书</dir>
	
	<br>
	<table>
		<tr>
			<td>Title</td>
			<td>Quantity</td>
			<td>Price</td>
			<td>&nbsp;</td>
		</tr>
			<c:forEach items="${sessionScope.ShoppingCart.items }" var="item">
			<tr>
				<td>${item.book.title }</td>
				<td>
				<input type="text" class="${item.quantity }" value="${item.quantity }" size="1" name="${item.book.id }" />
				</td>
				
				<td>${item.book.price }</td>
				<td><a href="bookServlet?method=remove&id=${item.book.id }&pageNo=${param.pageNo }" class="delete">删除</a></td>
			</tr>
			</c:forEach>
			<tr colspan="4">
				<th id="totalMoney">总金额：￥${sessionScope.ShoppingCart.totalMoney }</th>
			</tr>
				
	</table>
	</center>
	<br>
				<a href="bookServlet?method=getBooks&pageNo=${param.pageNo }">继续购物</a>
				<a href="bookServlet?method=clear&id=${item.book.id }&pageNo=${param.pageNo }">清空购物车</a>
				<a href="bookServlet?method=forward&page=cash">结账</a>
</body>
</html>