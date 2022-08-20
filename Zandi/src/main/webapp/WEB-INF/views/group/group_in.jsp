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

	<h1>${GROUP.g_name}의 스터디 룸</h1>
	
	<div>참여인원</div>
	<c:forEach items="${PEOPLELIST}" var="PEOPLE">
		<div>${PEOPLE.j_username}</div>
	</c:forEach>


</body>
</html>