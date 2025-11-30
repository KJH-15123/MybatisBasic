<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	

	<%@ include file="/WEB-INF/views/common/menubar.jsp" %>
	
	<!-- 
	
		boardListView에서 글작성 버튼 누르면 현재 페이지로 이동되고 
		현재 페이지에서 등록버튼을 누르면 글작성이 되도록 처리하기 
	
		BoardInsertController - doGet/doPost 각각 이용하기 
		insert.bo
		작성자 정보는 어떻게 넣을지 생각해보기 게시글등록시 필요한 작성자 정보 == 회원번호
	
	 -->
	<div class="outer" align="center">
		<br>
		<h2>게시글 등록</h2>
		
		<form action="${contextRoot }/insert.bo" method="post">
		
			<!-- 사용자에겐 보여주지 않고 데이터 전달하는 방법 -->
			<input type="hidden" name="userNo" value="${loginMember.userNo }">
			<table id="detail-area" align="center" border="1">
				<tr>
					<th width="100">제목</th>
					<td><input type="text" name="boardTitle"></td>
				</tr>
				<tr>
					<th>작성자</th>
					<td>${loginMember.userId}</td>
				</tr>
				<tr>
					<th>내용</th>
					<td>
						<textarea style="resize:none" cols="30" rows="10" name="boardContent"></textarea>
					</td>
				</tr>
			</table>
			<br>
			<div align="center">
				<button type="submit">등록</button>
			</div>
			
		</form>
	</div>
	
	
	
</body>
</html>