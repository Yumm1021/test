<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<form action="/result" method="post">
	<select name="deal_year">
		<c:forEach var="i" begin="2000" end="2020" step="1">
			<option value="${i}">${i}</option>
		</c:forEach>
	</select>
	
	<select name="deal_month">
		<c:forEach var="i" begin="1" end="12" step="1">
			<option value="${i}">${i}</option>
		</c:forEach>
	</select>
	
	<select name="regional_code">
		<c:forEach items="${list}" var="item">
			<option value="${item.external_cd}">${item.local_nm}</option>
		</c:forEach>	
	</select>
	<input type="submit" value="전송">
</form>
</body>
</html>
