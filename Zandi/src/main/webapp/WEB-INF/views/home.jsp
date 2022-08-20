<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
    <c:set value="${pageContext.request.contextPath}" var="rootPath" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
</head>

<head>

<body>
<h1>Zandi</h1>
	
	<c:choose>
		<c:when test="${LAYOUT == 'NOT_LOGIN'}">
			<a href="${rootPath}/user/login">로그인</a>
			<a href="${rootPath}/user/join">회원가입</a>
		</c:when>
		<c:otherwise>
			<a href="${rootPath}/git">레파지토리</a>
			<a href="${rootPath}/group">그룹</a>
			<a href="${rootPath}/user/logout">로그아웃</a>
		</c:otherwise>
	</c:choose>


</body>
</html>
