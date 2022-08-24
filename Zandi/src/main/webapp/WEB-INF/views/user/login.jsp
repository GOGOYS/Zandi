<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.error-message{
		color:red;
	}

</style>
</head>
<body>
	<form method="POST">
		<fieldset>
			<legend>로그인</legend>
				<c:if test="${error == 'LOGIN_FAIL'}">
					<div class="error-message">아이디나 비밀번호가 틀렸습니다</div>
				</c:if>
			<label for="id">ID : </label>
			<input name="u_username" placeholder="id를 입력하세요" id="id">
			<label for="pass">PASSWORD : </label>
			<input name="u_password" type="password" id="pass">
			<button>전송</button>
		</fieldset>
	</form>
</body>
</html>