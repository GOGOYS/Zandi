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
	<h1>repo detail</h1>
	
	<div>레파지토리 이름: ${REPONAME}</div>
	<!-- <div>README : ${README.content}</div> -->	
	<c:if test="${TODAYOK == 'OK' }" >
		<div class="repo-container">
	 		<span>${REPONAME} 오늘의 커밋 완료</span>
	 	</div>
	</c:if>
	<c:if test="${TODAYOK == 'NO' }" >
		<div class="repo-container">
	 		<span>${REPONAME} 오늘의 커밋 미완료</span>
	 	</div>
	</c:if>

	<h3>모든 커밋</h3>
	<c:forEach items="${GITLIST}" var="GITLIST">
	 		<div>커밋 날짜 : ${GITLIST.commit.author.date}</div>
			<div>깃 내용 : ${GITLIST.commit.message}</div>
	</c:forEach>
	
	<a href="${rootPath}/zandi/git">RepoList 가기</a>
	<a href="${rootPath}/zandi">Home 가기</a>
</body>
</html>