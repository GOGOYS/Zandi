<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>로그인</h1>
	<c:if test="${error == 'LOGIN_FAIL'}">
		<div>아이디나 비밀번호가 틀렸습니다</div>
	</c:if>
	<form method="POST">
		<input name="username">
		<input name="password">
		<button>전송</button>
	</form>
</body>
</html>