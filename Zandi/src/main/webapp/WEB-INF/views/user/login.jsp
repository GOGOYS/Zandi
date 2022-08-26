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
	
	.form-box{
		display: flex;
		flex-direction: column;
		width: 400px;
		height: 120px;
	}
	
	button{
		margin-top:6px;
		width: inherit;
	}

</style>
</head>
<body>
	<form method="POST" class="form-box">
		<fieldset>
			<legend>로그인</legend>
				<c:if test="${error == 'LOGIN_FAIL'}">
					<div class="error-message">아이디나 비밀번호가 틀렸습니다</div>
				</c:if>
			<div class="input-box">
				<label for="id">ID : </label>
				<input name="u_username" placeholder="id를 입력하세요" id="id">
				<label for="pass">PASSWORD : </label>
				<input name="u_password" type="password" id="pass">
			</div>
			<button>로그인</button>
		</fieldset>
	</form>
</body>
</html>