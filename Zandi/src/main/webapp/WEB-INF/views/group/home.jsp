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
	.in-container-box{
		background-color: green;
		border-radius: 8px;
		width: 300px;
		height: 100px;
		margin-bottom:6px;
	}
	
	.form-box{
		width:500px;
		margin-top:10px;
	}
	
	fieldset{
		display: flex;
		flex-direction: column;
	}
	
	.input-container{
		width:100%;
	}
	
	input[name="g_name"]{
		width:220px;
	}
	
</style>
</head>
<body>
	<div class="wrap-container">
		<h1>STUDY LIST</h1>
		<div>${USER}</div>
		<div>잔디: </div>
		<c:forEach items="${TROPHY}" var="TROPHY">
			<div>
				<img src="${rootPath}/static/image/trophy.png" title="스터디: ${TROPHY.t_groupname} / 완료율: ${TROPHY.t_complete}" alt="완료율에 따른 트로피">
			</div>
		</c:forEach>
		
		<c:forEach items="${GROUPLIST}" var="GROUP">
			<div class="in-container-box">
				<div>스터디방 : ${GROUP.g_name}</div>
				<div>참여 인원 : ${GROUP.g_inpeople} / ${GROUP.g_people}</div>
				<a href="${rootPath}/group/group_in/${GROUP.g_seq}">참가하기</a>		
			</div>		
		</c:forEach>

		<form class="form-box" method="POST" >
			<fieldset>
				<legend>스터디 생성하기</legend>
				<div class="input-container">
						<label>스터디 이름 </label>
						<input name="g_name" placeholder="생성할 그룹 이름을 입력하세요">
						<label>인원제한 </label>
						<input name="g_people" type="number"  value="4" min="4" max="20" >
						<label>기간 D-</label>
						<input name="end_date" type="date" value="2022-09-30" >
				</div>
				<button>생성</button>
			</fieldset> 
		</form>
		
		<a href="${rootPath}/">홈으로 가기</a>
	</div>
</body>
</html>