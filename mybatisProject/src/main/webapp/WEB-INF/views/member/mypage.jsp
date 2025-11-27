<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<%@ include file="/WEB-INF/views/common/menubar.jsp"%>
	
	
	<div class="outer">	
		<h1 align="center">마이 페이지</h1>
	
		<!-- 
			MemberUpdateController 
			updateMember 
			처리 후 마이페이지로 재요청
			성공시 변경된 로그인정보 갱신하기 (아이디 이용하여 회원정보 전체조회하는 구문도 작성 : selectMember)
			성공 메시지 또는 실패 메시지 alert에 담아주기 
		
		 -->
		
	
		<form action="update.me" method="post">
			<table align="center">
				<tr>
					<td>*ID</td>
					<td><input type="text" name="userId" value="${loginMember.userId}"  readonly></td>
				</tr>
				<tr>
					<td>*NAME</td>
					<td><input type="text" name="userName" required value="${loginMember.userName}"></td>
				</tr>
				
				<tr>
					<td>EMAIL</td>
					<td><input type="email" name="email"value="${loginMember.email}"></td>
				</tr>
				
				<tr>
					<td>BIRTHDAY</td>
					<td><input type="text" name="birthday"  placeholder="생년월일(6자리)"value="${loginMember.birthday}"></td>
				</tr>
				
				<tr>
					<td>GENDER</td>
					<td>
						<input type="radio" name="gender" value="M"> 남
						<input type="radio" name="gender" value="F"> 여
					</td>
				</tr>
				<tr>
					<td>PHONE</td>
					<td><input type="text" name="phone" placeholder="-포함" value="${loginMember.phone}"></td>
				</tr>
				<tr>
					<td>ADDRESS</td>
					<td><input type="text" name="address" value="${loginMember.address}" ></td>
				</tr>
			</table>
			<br>
			<div align="center">
				<button type="submit">정보수정</button>
			</div>
		</form>
	</div>
	
	<script>
	
		$(function(){
			//input요소중 value값에 M,F 인 요소 선택하여 checked 속성 true로 변경
			
			console.log($("input[name=gender]"));
			console.log($("input[value=${loginMember.gender}]"));
			
			//value속성이 loginMember가 가지고 있는 gender값과 일치하면 체크시키기
			$("input[value=${loginMember.gender}]").prop("checked",true);
					

		});	
		
	
	</script>
	
	
	
</body>
</html>