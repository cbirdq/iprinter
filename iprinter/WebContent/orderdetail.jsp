<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div style="border: 1px solid #CCC">
	<table width="100%">
		<tr><td>${order.createtime}</td><td>订单号：${order.id }</td><td>订单总额：${order.money }</td><td>支付方式：${order.payway}</td><td>${order.status }</td></tr>
		<tr><td colspan="5">
			<table width="100%">
			<c:forEach items="${order.entries }" var="entry" >
			<tr><td>${entry.file.originalname }</td><td>${entry.printcount }</td><td>${entry.money }</td></tr>
			</c:forEach>				
			</table>
		</td></tr>
	</table>
</div>
</body>
</html>