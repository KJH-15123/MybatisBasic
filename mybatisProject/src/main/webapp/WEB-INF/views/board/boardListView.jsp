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
	 
	<div class="outer">
		<br>
		<h2 align="center">게시판</h2>	
		<br><br>
		
		<div id="search-area" align="center">
		</div>
		
		<script>
			$(function(){
			

			});
		
		</script>
		
		
		<%-- 글작성 버튼은 로그인한 회원일 경우 보일 수 있도록 조건처리 --%>
		
		
			<div align="center">
				<a href="${contextPath }/insert.bo" class="btn btn-info">글작성</a>
			</div>
		
		<br>
		
		<table class='list-area' align='center' border='1'>
			<thead>
				<tr>
					<th width='70'>글 번호</th>
					<th width='300'>제목</th>
					<th width='100'>작성자</th>
					<th width='50'>조회수</th>
					<th width='100'>작성일</th>
				</tr>
			</thead>
			<tbody>
			
			</tbody>
		</table>
			
				
				
		
		
		
		
		
		
		<br><br>
		
		<div align="center" class="paging-area">
		
		</div>
		
		<br><br>
	</div>
	
</body>
</html>