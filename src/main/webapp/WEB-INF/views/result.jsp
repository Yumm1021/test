<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Result</title>
</head>
<body>



	<c:if test="${list.size() > 0 }">
		지역명 : ${list[0].local_nm}
	<div>
		<table>
			<tr>
				<th>법정동</th>
				<th>지번</th>
				<th>아파트명</th>
				<th>거래금액</th>
				<th>건축년도</th>
				<th>년</th>
				<th>월</th>
				<th>일</th>
				<th>전용면적</th>
				<th>층</th>
			</tr>
		
			<c:forEach items="${list}" var="item">
				<tr>
					<td>${item.dong}</td>
					<td>${item.jibun}</td>
					<td>${item.apartment_name}</td>
					<td>${item.deal_amount}</td>
					<td>${item.build_year}</td>
					<td>${item.deal_year}</td>
					<td>${item.deal_month}</td>
					<td>${item.deal_day}</td>
					<td>${item.area_for_exclusive_use}</td>
					<td>${item.flr}</td>
				</tr>
			</c:forEach>
		</table>
	</div> 
	</c:if>
	<c:if test="${list.size() == 0}">
		<div>글이 없습니다.</div>
	</c:if>
</body>
</html>