package com.kh.mybatis.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.mybatis.member.model.service.MemberService;
import com.kh.mybatis.member.model.vo.Member;

/**
 * Servlet implementation class MemberInsertController
 */
@WebServlet("/insert.me")
public class MemberInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/views/member/memberEnrollForm.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//회원 정보를 입력하고 회원가입 버튼을 눌렀을때 전달된 데이터들 추출하기
		request.setCharacterEncoding("UTF-8");
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		String userName = request.getParameter("userName");
		String email = request.getParameter("email");
		String birthday = request.getParameter("birthday");
		String gender = request.getParameter("gender");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		
		//객체에 담기
		Member m = new Member(userId,userPwd,userName,email,birthday,gender,phone,address);
		
		//service에 전달 및 응답처리하기
		
		//insert 구문(DML)
		int result = new MemberService().insertMember(m);
		
		//처리된 결과에 따라서 화면 지정
		
		//세션에 메시지 담아주기
		HttpSession session = request.getSession();
		
		String alertMsg = null;
		if(result>0) {
			alertMsg ="회원가입 성공!";
		}else {
			alertMsg = "회원가입 실패!"; 
		}
		
		session.setAttribute("alertMsg", alertMsg);
		
		response.sendRedirect(request.getContextPath());
	}

}
