<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<c:set value="${pageContext.request.contextPath}" var="rootPath" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>그룹 만들기</h1>


	<c:forEach items="${GROUPLIST}" var="GROUP">
		<div>
			<div>${GROUP.g_name}</div>
			
			<c:choose>
				<c:when test="${MESSAGE == 'NOTJOIN' }">
					<a href="${rootPath}/group/join/${GROUP.g_seq}">참가하기</a>				
				</c:when>
				<c:otherwise>
					<a href="${rootPath}/group/group_in/${GROUP.g_seq}">입장하기</a>
				</c:otherwise>
			</c:choose>
		</div>		
	</c:forEach>




	<form method="POST">
		<input name="g_name" placeholder="생성할 그룹 이름?">
		<input name="g_people" placeholder="인원제한">
		<button>생성</button> 
	</form>
</body>
</html>