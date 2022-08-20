<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
    <c:set value="${pageContext.request.contextPath}" var="rootPath" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>My Zandi</h1>
	<h2>My Repo</h2>
	
	<c:if test="${not empty REPOLIST}">
		<c:forEach items="${REPOLIST}" var="REPO">
			<div data-set="${REPO.r_seq}">
				<div>${REPO.r_reponame}</div>
			</div>
		</c:forEach>
	</c:if>


	<form method="POST">
		<fieldset>
			<legend>repository add</legend>
			<input name="repo" placeholder="github repository 이름을 입력하세요">
			<button>add</button>
		</fieldset>
	</form>

</body>
</html>