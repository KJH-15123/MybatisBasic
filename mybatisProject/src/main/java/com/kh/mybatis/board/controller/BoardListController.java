package com.kh.mybatis.board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.mybatis.board.model.service.BoardService;
import com.kh.mybatis.board.model.vo.Board;
import com.kh.mybatis.common.model.vo.PageInfo;

/**
 * Servlet implementation class BoardListController
 */
@WebServlet("/list.bo")
public class BoardListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//현재 페이지 수 (위치)
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		//가지고 있는 총 게시글 개수 
		//board-mapper(boardMapper) /alias = Board
		int listCount = new BoardService().listCount();
		//한 페이지에서 보여질 개수 
		int boardLimit = 5;
		//페이징바를 몇개까지 보여줄지 개수
		int pageLimit = 10;
		
		//계산 처리시 보여질 끝 페이지 수 (endPage보다 작을 수 있음)  
		int maxPage = 0;//마지막 페이지 
		/*
		 * maxPage는 listCount와 boardLimit의 영향을 받는 수 
		 * 
		 * 총개수/한페이지에서 보여질개수
		 * listCount/boardLimit 를 double계산 처리 후 올림처리 후 정수처리
		 * */
		maxPage = (int)Math.ceil((double)listCount/boardLimit);
		
		//페이징 처리시 보여질 시작 페이지 수 
		int startPage = 0;
		/*
		 * startPage는 currentPage와 pageLimit에 영향을 받는 수 
		 * pageLimit = 10인경우 
		 * 
		 * currentPage가 1~10까지일땐 startPage는 1 
		 * currentPage가 11~20까지일땐 startPage는 11
		 * currentPage가 21~30까지일땐 startPage는 21
		 * currentPage가 31~40까지일땐 startPage는 31
		 * ..
		 * */
		startPage = (currentPage-1)/pageLimit * pageLimit +1;
		
		//페이징 처리시 보여질 끝 페이지 수
		/*
		 * endPage 는 startPage 와 pageLimit에 영향을 받는 수 
		 * 
		 * */
		int endPage = startPage+pageLimit-1;
		
		//최대페이지수(maxPage)가 endPage수보다 작을때 endPage를 maxPage수로 변경하기
		if(maxPage<endPage) {
			endPage = maxPage; //maxPage에 있는 값을 endPage에 대입하기
		}
		
		//페이징처리에 필요한 값들을 담아 전달할 수 있는 객체 준비하기 
		PageInfo pi = new PageInfo(listCount,currentPage,pageLimit,boardLimit,maxPage,startPage,endPage);
		
		//게시글 목록 조회
		ArrayList<Board> list = new BoardService().selectList(pi);
		
		
		//뷰페이지로 위임할때 목록 정보와 페이징요소 전달하기
		request.setAttribute("list", list);
		request.setAttribute("pi", pi);
		
		
		
		request.getRequestDispatcher("/WEB-INF/views/board/boardListView.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
