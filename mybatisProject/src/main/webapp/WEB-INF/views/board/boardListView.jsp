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
		
		<c:if test="${not empty loginMember }">
			<div align="center">
				<a href="${contextRoot}/insert.bo" class="btn btn-info">글작성</a>
			</div>
		</c:if>
		
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
				<!-- 
					조회해온 게시글 목록을 화면에 출력하기 
					만약 게시글이 없다면 조회된 게시글이 없습니다 로 나올 수 있도록 처리하기
					
					특정 글을 클릭했을때 해당 글의 글번호를 추출하여 컨트롤러에 전달하기 
					자바스크립트 또는 제이쿼리 문법 이용해서 클릭이벤트로 처리해보기 
					
					서블릿명 : BoardDetailController / 매핑주소 :  detail.bo / 글번호 변수명 : bno 
					메소드명 : selectBoard 
					조회해올 데이터 컬럼 : 조회수,제목,작성자(아이디),작성일,내용 
					boardDetailView 에 알맞은 위치에 정보 띄워주기
					
				 -->
				 <c:choose>
				 	<c:when test="${empty list}">
				 		<tr>
				 			<td colspan='5'>조회된 게시글이 없습니다.</td>
				 		</tr>
				 	</c:when>
				 	<c:otherwise>
				 		<!-- 글목록이 있을 경우 반복문을 이용하여 전부 뽑아주기 -->
						<c:forEach items="${list }" var="b">
							<tr>
								<td>${b.boardNo }</td>
								<td>${b.boardTitle }</td>
								<td>${b.boardWriter }</td>
								<td>${b.count }</td>
								<td>${b.createDate }</td>
							</tr>
						</c:forEach>				 	
				 	</c:otherwise>
				 </c:choose>
			</tbody>
		</table>
		
		<script>
			$(function(){
				$(".list-area tbody tr").click(function(){
					//현재 클릭이벤트가 발생한 위치 $(this)
					//$(this) == tr 원하는 요소는 tr의 자식요소중 글번호를 가진 요소
					//tr의 첫번째 자식의 text 
					let bno = $(this).children().first().text();
	
					//경로로 이동시키기
					location.href='${contextRoot}/detail.bo?bno='+bno;
				});
			});
		</script>
		
		
		
		
		<br><br>
		
		<div align="center" class="paging-area">
			
			<c:forEach var="i" begin="${pi.startPage}" end="${pi.endPage }">
				<button onclick="location.href='${contextRoot}/list.bo?currentPage=${i}'">${i}</button>
			</c:forEach>
			
		</div>
		
		<br><br>
	</div>
	
</body>
</html>