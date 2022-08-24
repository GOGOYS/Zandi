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
	<div class="container">
		<form method="POST">
			<fieldset>
				<legend>회원가입</legend>
				<div class="con">
					<div>
						<label for="username">ID : </label>
						<input name="u_username" placeholder="ID를 입력하세요" id="username">
					</div>
					<div>
						<label for="password">PASSWORD : </label>
						<input name="u_password" type="password" id="password">
					</div>
					<div>
						<label for="re_password">PASSWORD CEHCK : </label>
						<input name="re_password" type="password" id="re_password">
					</div>
					<div>
						<label for="github_id">GITHUB ID : </label>
						<input name="u_github_id" placeholder="github name을 입력하세요" id="github_id">
					</div>
					<div>
						<label for="email">EMAIL : </label>
						<input name="u_email" placeholder="email을 입력하세요" id="email">
					</div>
					<div>
						<button>회원가입</button>
					</div>
				</div>
			</fieldset>
		</form>
	</div>
</body>
</html>