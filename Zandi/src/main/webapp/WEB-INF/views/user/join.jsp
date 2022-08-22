<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.con{
		display:  flex;
		flex-direction: column;
	}
</style>
</head>
<body>
	<form method="POST">
		<fieldset>
			<legend>회원가입</legend>
			<div class="con">
				<div>
					<label for="username">ID : </label>
					<input name="username" placeholder="github name을 입력하세요" id="username">
				</div>
				<div>
					<label for="password">PASSWORD : </label>
					<input name="password" type="password" id="password">
				</div>
				<div>
					<label for="re_password">PASSWORD CEHCK : </label>
					<input name="re_password" type="password" id="re_password">
				</div>
				<div>
					<label for="email">EMAIL : </label>
					<input name="email" placeholder="email을 입력하세요" id="email">
				</div>
				<div>
					<button>회원가입</button>
				</div>
			</div>
		</fieldset>
	</form>

</body>
</html>