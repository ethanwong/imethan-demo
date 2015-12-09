<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>view order</title>
</head>
<body>
	<h1>Order</h1>
	<c:choose>
		<c:when test="${empty cart.items}">
			<p>Your cart is empty.</p>
		</c:when>
		<c:otherwise>
			<table border="1" cellspacing="0">
				<tr>
					<th>Item</th>
					<th>Quantity</th>
					<th>Unit Price</th>
					<th>Total</th>
				</tr>

				<c:forEach var="item" items="${cart.items}">
					<tr>
						<td>${item.product.name}</td>
						<td>${item.quantity}</td>
						<td>${item.product.price}</td>
						<td>${item.totalPrice}</td>
					</tr>
				</c:forEach>

				<tr>
					<td>TOTAL:</td>
					<td></td>
					<td></td>
					<td>${cart.totalPrice}</td>
				</tr>
			</table>
		</c:otherwise>
	</c:choose>
	
	<h2>Products for Your Choice</h2>

	<table>
	<c:forEach var="product" items="${products}">
		<tr>
			<td>${product.name}</td>
			<td>${product.price}</td>
		</tr>			
	</c:forEach>
	</table>
	
	
	<a href="${flowExecutionUrl}&_eventId=confirm">Confirm</a>
</body>
</html>