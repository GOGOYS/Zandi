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
	.people-container{
		display: flex;
		width:500px;
	}
	
	.people{
		margin-right: 10px;
	}
</style>
</head>
<body>
	<%@ include file="/WEB-INF/views/load.jsp" %>

	<h1>${GROUP.g_name}의 스터디 룸</h1>
	
	<h3>참여인원</h3>
	<div class="people-container">
		<c:forEach items="${PEOPLELIST}" var="PEOPLE">
			<div class="people">${PEOPLE.j_username}</div>
		</c:forEach>
	</div>
	
	<c:forEach items="${TOOK}" var="TOOK">
		<div>
			<div>${TOOK.username}님의</div>
			<div>오늘 커밋 : ${TOOK.message}</div>
		</div>
	</c:forEach>
	
	<form  method="POST">
		<fieldset>
			<legend> Comment</legend>
			<input placeholder="내용을 입력하세요">
		</fieldset>
	</form>
	
	<a href="${rootPath}/group/list">리스트 가기</a>
	<a href="${rootPath}/group/out/${GROUP.g_seq}">스터디방 탈퇴하기</a>

</body>
</html>