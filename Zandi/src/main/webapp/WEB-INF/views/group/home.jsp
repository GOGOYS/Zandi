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

	*{
		box-sizing: border-box;
		list-style: none;
		margin:0;
		padding:0;
	}
	.in-box{
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
	
	.zandi-container{
		display: flex;
	}
	
	.zandi-img{
		margin-left: 6px;
	}
	
</style>
</head>
<body>
	<div class="wrap-container">
		<h1>STUDY LIST</h1>
		<div class="zandi-container">
			<div>${USER}님의 잔디 : </div>
			<c:forEach items="${TROPHY}" var="TROPHY">
					<img  class="zandi-img" src="${rootPath}/static/image/trophy.png" title="${TROPHY.t_groupname}의 잔디" alt=" ${TROPHY.t_groupname} 트로피">
			</c:forEach>
		</div>
		<c:if test="${not empty FULLIN}">
			<div>${FULLIN}의 입장 정원이 초과했습니다</div>
		</c:if>	
		
		<div class="container-box">
			<c:forEach items="${GROUPLIST}" var="GROUP">
					<div class="target in-box" data-seq="${GROUP.g_seq}">
						<div>스터디방 : ${GROUP.g_name}</div>
						<div>참여 인원 : ${GROUP.g_inpeople} / ${GROUP.g_people}</div>
						<c:choose>
							<c:when test="${empty GROUP.period}">D-DAY</c:when>	
							<c:otherwise>D-${GROUP.period}</c:otherwise>
						</c:choose>
					</div>
			</c:forEach>
		</div>		

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
<script>
	const rootPath = "${rootPath}"
</script>
<script src="${rootPath}/static/js/group.js?var=2022-09-06-002"></script>
</html>