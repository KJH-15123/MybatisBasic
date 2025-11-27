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
 * Servlet implementation class MemberUpdateController
 */
@WebServlet("/update.me")
public class MemberUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		String userId = request.getParameter("userId");
		String userName = request.getParameter("userName");
		String email = request.getParameter("email");
		String birthday = request.getParameter("birthday");
		String gender = request.getParameter("gender");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		
		//변경하려는 내용들 객체에 담기
		Member m = new Member(userId,userName,email,birthday,gender,phone,address);
		MemberService service = new MemberService();
		
		int result = service.updateMember(m);
		
		HttpSession session = request.getSession();
		
		String alertMsg=null;
		if(result>0) {
			alertMsg = "수정 성공!";
			//회원정보 바로 적용(갱신)
			Member memberinfo = service.selectMember(m);
			session.setAttribute("loginMember", memberinfo);
		}else {
			alertMsg = "수정 실패!";
		}
		session.setAttribute("alertMsg", alertMsg);
		
		response.sendRedirect(request.getContextPath()+ "/mypage.me");
	}

}
