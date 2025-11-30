<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<style>
.outer {
		width : 900px;
		background-color: black;
		color : white;
		margin : auto;
		margin-top : 50px;
	}
	
	.login-area{
		text-decoration: none;
		color : black;
		font-size : 12px;
	}
	
	.nav-area{
		background-color: black;
		color : white;
		height : 50px;
	}
	
	.menu{
		width : 250px;
		height : 50px;
		vertical-align : middle;
		font-size : 20px;
		font-weight : bold;
		display : table-cell;
	}
	
	.menu:hover{
		background-color: darkgray;
		cursor : pointer;
	}

</style>

</head>
<body>
	<!-- 알림 메시지가 담겨있다면 -->
	<c:if test="${not empty alertMsg }">
		<script>
			//메시지 띄우기
			alert("${alertMsg}");
		</script>
		<!-- 더이상 필요없으니 지우기 -->
		<c:remove var="alertMsg" />
	</c:if>

	<!-- 
		MemberLogoutController 만들어서 처리하기 
		get방식으로 요청 
		힌트는 loginMember를 지우기 -> 메인페이지로 재요청
		
	 -->
	<!-- contextPath 가 길어지니 변수처리해서 짧게 쓰기 -->
	<c:set var="contextRoot" value="${pageContext.request.contextPath }" />

	<h1 align="center">Hello Mybatis</h1>

	<div class="login-area" align="right">
		<!-- 로그인 전 화면과 로그인 후 화면 구성하기  -->

		<c:choose>
			<c:when test="${empty loginMember }">
				<form action="${pageContext.request.contextPath}/login.me"
					method="post">
					<%-- 		동적으로 contextRoot 받아오기 	
					<%=request.getContextPath() %> --%>
					<table>
						<tr>
							<td>아이디</td>
							<td><input type="text" name="userId" required></td>
							<td rowspan="2"><button type="submit">로그인</button></td>
						</tr>
						<tr>
							<td>비밀번호</td>
							<td><input type="password" name="userPwd" required>
							</td>
						</tr>
						<tr>
							<td colspan="3"><a href="${contextRoot}/insert.me">회원가입</a>
								<a>ID/PWD 찾기</a></td>
						</tr>
					</table>
				</form>
			</c:when>
			<c:otherwise>
				<div>
					<table>
						<tr>
							<td colspan="2">
								<h3>${loginMember.userName}님환영합니다.</h3>
							</td>
						</tr>
						<tr>
						
						<!-- 로그인이 되어있지 않은채로 mypage요청을 한다면
							로그인 후 이용 가능한 서비스 입니다 라는 메시지를 띄우고 메인페이지로 보내기
							로그인 되어있을 경우 마이페이지로 이동
						 -->
						
							<td><a href="${contextRoot}/mypage.me">마이페이지</a></td>
							<td><a href="${contextRoot}/logout.me">로그아웃</a></td>
						</tr>
					</table>
				</div>
			</c:otherwise>
		</c:choose>
	</div>

	<br>

	<div class="nav-area" align="center">
		<div class="menu" onclick="location.href='${contextRoot}'">HOME</div>
		<div class="menu" >공지사항</div>
		<div class="menu" onclick="location.href='${contextRoot}/list.bo?currentPage=1'">게시판</div>
		<div class="menu">ETC</div>
	</div>




</body>
</html>