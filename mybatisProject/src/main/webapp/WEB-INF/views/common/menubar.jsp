<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.outer{
		width:900px;
		background-color:black;
		color:white;
		margin:auto;
		margin-top:50px;
	}
	
	.login-area{
		text-decoration:none;
		color:white;
		font-size:12px;
	}
	
	.nav-area{
		background-color:black;
		color:white;
		height:50px;
	}
	
	.menu{
		width:250px;
		height:50px;
		vertical-align:middle;
		font-size:20px;
		font-weight:bold;
		display:table-cell;
	}
	
	.menu:hover{
		background-color:darkgray;
		cursor:pointer;
	}
	
</style>
</head>
<body>

	<div class="outer">
		
		<h1 align="center">Hello Mybatis</h1>
<%-- 동적으로 contextRoot 받아오기 --%>		
		<div class="login-area" align="right">
			<!-- 로그인 전 화면과 로그인 후 화면 구성하기 -->
			확인 : ${pageContext.request.contextPath }
			<form action="${pageContext.request.contextPath }/login.me" method="post">
				<table>
					<tr>
						<td>아이디</td>
						<td><input type="text" name="userId" required></td>
						<td rowspan="2"><button type="submit">로그인</button></td>
					</tr>
					<tr>
						<td>비밀번호</td>
						<td><input type="password" name="userPwd" required></td>
					</tr>
					<tr>
						<td colspan="3">
							<a>회원가입</a>
							<a>ID/PWD 찾기</a>
						</td>
					</tr>
				</table>
			</form>
		</div>
		
		<br>
		<div class="nav-area" align="center">
			<div class="menu">HOME</div>
			<div class="menu">공지사항</div>
			<div class="menu">게시판</div>
			<div class="menu">ETC</div>
		</div>
		
	</div>
	

</body>
</html>