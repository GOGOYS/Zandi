<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<c:set value="${pageContext.request.contextPath}" var="rootPath" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.container{
		background-color: green;
		border-radius: 8px;
		width: 300px;
		height: 100px;
		margin-bottom:6px;
	}
	
	form{
		width:300px;
	}
	
	fieldset{
		display: flex;
		flex-direction: column;
	}
	
</style>
</head>
<body>
	<div class="container">
		<h1>STUDY LIST</h1>
		<c:forEach items="${GROUPLIST}" var="GROUP">
			<div class="container">
				<div>스터디방 : ${GROUP.g_name}</div>
				<div>참여 인원 : ${GROUP.g_inpeople} / ${GROUP.g_people}</div>
				<a href="${rootPath}/group/group_in/${GROUP.g_seq}">참가하기</a>		
			</div>		
		</c:forEach>

		<form method="POST" >
			<fieldset>
				<legend>스터디 생성하기</legend>
				<input name="g_name" placeholder="생성할 그룹 이름을 입력하세요">
				<input name="g_people" type="number" placeholder="2~20명 까지 인원제한을 정해주세요" min="2" max="20" >
				<button>생성</button>
			</fieldset> 
		</form>
	</div>
</body>
</html>