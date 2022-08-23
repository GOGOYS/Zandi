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
	
	.repo-box{
		width:300px;
		height: 60px;
		background-color: skyblue;
		border-radius: 8px;
		margin-bottom: 6px;
	}
	.repo-box span{
		color:white;
		font-weight: 900;
	}
	
	form{
		width: 300px;
	}
</style>
</head>
<body>
	<h1>My Zandi</h1>
	
	<div class="zandi-img">
		<img src="https://ghchart.rshah.org/${USER.username}" />
	</div>
	
	<h2>My Repo</h2>
	<c:if test="${not empty REPONAME}">
		<div class="repo-container">
			<c:forEach items="${REPONAME}" var="REPO" varStatus="INDEX">
				<div class="target repo-box" data-seq="${INDEX.count}">
					<span>${INDEX.count}</span>
					<span>${REPO}</span>
				</div>
			</c:forEach>
		</div>
	</c:if>

</body>
<script>
	const rootPath = "${rootPath}"
</script>

<script src="${rootPath}/static/js/repo.js?var=2022-08-22-001"></script>
</html>