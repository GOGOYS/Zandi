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

	body{
		margin: 0;
		padding:0;
		display: inline-block;
		width: 100%;
	}
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

	<h1>${GROUP.g_name}의 스터디 룸</h1>
	<c:choose>
		<c:when test="${DDAY == DAY}">스터디방의 남은 기간 : D-DAY</c:when>	
		<c:otherwise>스터디방의 남은 기간 : D-${DDAY}</c:otherwise>
	</c:choose>
	<div>스터디 기간안에 출석률 80% 이상을 달성하면 ${GROUP.g_name}잔디가 생겨요!</div>

	<div class="peopleList-box">
		<h3>참여인원</h3>
		<table>
			<thead>	
				<tr>	
					<th>이름</th>
					<th>출석률</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${PEOPLELIST}" var="PEOPLE">
					<tr>
						<td class="people">${PEOPLE.j_username}</td>
						<td class="people">${PEOPLE.j_percent}%</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<c:forEach items="${TOOK}" var="TOOK">
		<div>
			<div>${TOOK.username}님의</div>
			<div>오늘 커밋 : ${TOOK.message}</div>
		</div>
	</c:forEach>
	
	<c:forEach items="${COMMENT}" var="COMMENT">
		<div>
			<div>
				<div>${COMMENT.c_username}</div>
				<div>${COMMENT.c_date} ${COMMENT.c_time}</div>
			</div>
			<div>
				<div>${COMMENT.c_comment}</div>
			</div>
		</div>
	</c:forEach>
	<form  method="POST">
		<fieldset>
			<legend> Comment</legend>
			<textarea name="c_comment" placeholder="내용을 입력하세요" cols="40" rows="4"></textarea>
			<button>작성</button>
		</fieldset>
	</form>
	
	<a href="${rootPath}/group/list">리스트 가기</a>
	<a href="${rootPath}/group/out/${GROUP.g_seq}">스터디방 탈퇴하기</a>

</body>
</html>