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
	
	.header{
		margin:0 auto;	
	}
	
	.form-box{
		display: flex;
		flex-direction: column;
		width: 500px;
		margin: 0 auto;
	}
	
	button{
		margin-top:6px;
		width: 380px;
		background-color: blue;
		color:white;
		border: none;
	}
	
	.input-box input{
		width: 380px;
	}

</style>
</head>
<body>
	<div class="header">
		<div>로그인</div>
	</div>
	<form method="POST" class="form-box">
				<c:if test="${error == 'LOGIN_FAIL'}">
					<div>
						<div class="error-message">아이디나 비밀번호가 틀렸습니다</div>
					</div>
				</c:if>
			<div class="input-box">
				<input name="u_username" placeholder="아이디" id="id">
				<input name="u_password" placeholder="비밀번호" type="password" id="pass">
			</div>
			<button>로그인</button>
	</form>
</body>
</html>