package com.kh.mybatis.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.mybatis.board.model.service.BoardService;
import com.kh.mybatis.board.model.vo.Board;
import com.kh.mybatis.member.model.vo.Member;

/**
 * Servlet implementation class BoardInsertController
 */
@WebServlet("/insert.bo")
public class BoardInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		request.getRequestDispatcher("/WEB-INF/views/board/boardEnrollForm.jsp").forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//등록 요청시 전달받은 데이터 추출
		request.setCharacterEncoding("UTF-8");
		//input hidden으로 전달받은 데이터로 추출하기
		String userNo = request.getParameter("userNo");
		String boardTitle = request.getParameter("boardTitle");
		String boardContent = request.getParameter("boardContent");
		
		
		HttpSession session = request.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");
		
		//세션정보 이용하기 (로그인된 사용자 정보)
		//String userno = String.valueOf(loginMember.getUserNo());
		
		Board b = new Board();
		b.setBoardContent(boardContent);
		b.setBoardTitle(boardTitle);
		b.setBoardWriter(userNo); //작성자 번호 넣을땐 숫자로 
		
		int result = new BoardService().insertBoard(b);
		
		
		if(result>0) {
			session.setAttribute("alertMsg","게시글 등록 성공!");
			
		}else {
			session.setAttribute("alertMsg","게시글 등록 실패!");
		}
		
		response.sendRedirect(request.getContextPath()+"/list.bo?currentPage=1");
	}

}
