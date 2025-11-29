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

/**
 * Servlet implementation class BoardDetailController
 */
@WebServlet("/detail.bo")
public class BoardDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardDetailController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		int bno = Integer.parseInt(request.getParameter("bno"));
		
		BoardService service = new BoardService();
		//조회수 증가 처리 
		int result = service.increaseCount(bno);
		
		
		HttpSession session = request.getSession();
		
		if(result>0) { //조회수 증가 성공 - 해당 게시글 정보 조회하기 (selectBoard) 
			Board b = service.selectBoard(bno);
			
			request.setAttribute("b", b);
			
			//조회 성공시 상세보기 페이지로 위임
			request.getRequestDispatcher("/WEB-INF/views/board/boardDetailView.jsp").forward(request, response);
			
		}else { //에러페이지 또는 에러메시지 보내고 메인페이지 또는 게시글 목록 페이지로 이동시키기
			
			session.setAttribute("alertMsg", "상세조회 실패");
			//실패했으니 목록으로 되돌려주기(이전 페이지 정보가 있다면 그곳으로 보내기 )
			//request.getHeader("referer") : 요청 header 정보중 referer 은 이전 주소값을 가지고 있다.
			
			String ref = request.getHeader("referer");
			
			if(ref==null) {
				//목록으로 보내기 (이전 주소정보 없을때)
				response.sendRedirect(request.getContextPath()+"/list.bo?currentPage=1");
			}else {
				//이전 주소 정보 있을경우 거기로 보내기
				response.sendRedirect(ref);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
