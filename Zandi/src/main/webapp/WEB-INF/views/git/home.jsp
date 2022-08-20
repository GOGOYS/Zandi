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
	
	<div>
		<img src="https://ghchart.rshah.org/${USER.username}" />
	</div>
	
	<h2>My Repo</h2>
	<c:if test="${not empty REPOLIST}">
		<div class="container">
			<c:forEach items="${REPOLIST}" var="REPO">
				<div class="target" data-seq="${REPO.r_seq}">
					<span>${REPO.r_reponame}</span>
				</div>
			</c:forEach>
		</div>
	</c:if>


	<form method="POST">
		<fieldset>
			<legend>repository add</legend>
			<input name="repo" placeholder="github repository 이름을 입력하세요">
			<button>add</button>
		</fieldset>
	</form>

</body>
<script>
	const rootPath = "${rootPath}"
</script>

<script src="${rootPath}/static/js/repo.js?var=2022-08-20-003"></script>
</html>