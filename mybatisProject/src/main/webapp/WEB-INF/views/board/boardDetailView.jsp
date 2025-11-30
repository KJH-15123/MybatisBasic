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

	<div class="outer" align="center">
		<br>
		<h2>게시글 상세보기</h2>
		
		<table id="detail-area" align="center" border="1">
			<tr>
				<th width="70">조회수</th>
				<td width="70">${b.count }</td>
				<th width="70">제목</th>
				<td width="350">${b.boardTitle }</td>
			</tr>
			<tr>
				<th>작성자</th>
				<td>${b.boardWriter }</td>
				<th>작성일</th>
				<td>${b.createDate }</td>
			</tr>
			<tr>
				<th>내용</th>
				<td colspan="3">
					<p style="height:200px; white-space:pre;">${b.boardContent }</p>
				</td>
			</tr>
		</table>
		<br>
		<div align="center">
			<button>목록가기</button>
			<button>수정하기</button>
			<button>삭제하기</button>
		</div>
		
		<br><br>
		<!-- 댓글 목록 -->
		<div id="reply-area">
			<table border="1" align="center">
				<thead>
					<tr>
						<th>댓글 작성</th>
						<td>
							<textarea id="replyContent" rows="3" cols="50" style="resize:none"></textarea>
						</td>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>작성자</td>
						<td>내용</td>
						<td>작성일</td>
					</tr>
				
										
				</tbody>
			</table>
		</div>
		<br><br>
	</div>
	
	
	
</body>
</html>