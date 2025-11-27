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
 * Servlet implementation class loginController
 */
@WebServlet("/login.me")
public class loginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		
		System.out.println(userId+"/"+userPwd);
		
		//service에 데이터 전달 및 요청
		//마이바티스 구문은 매개변수 전달이 하나만 가능하기때문에 데이터가 여러개일 경우 하나의 묶음으로 처리하여 요청하기
		Member m = new Member();
		m.setUserId(userId);
		m.setUserPwd(userPwd);
		
		Member loginMember = new MemberService().loginMember(m);
		
		//페이지가 이동되어도 로그인이 풀리지 않고 로그인 정보를 계속 가지고 다닐 수 있도록
		//session에 담아주기 HttpSession
		//session은 모든 jsp 모든 servlet에서 접근 가능한 공유 범위
		HttpSession session = request.getSession();
		
		if(loginMember!=null) {
			//로그인 정보가 있으면
			session.setAttribute("loginMember",loginMember);
			session.setAttribute("alertMsg", "로그인 성공!");
		} else {
			session.setAttribute("alertMsg", "로그인 실패!");
		}
		
		//로그인 처리가 되었으니 기존 요청을 마무리하고 메인페이지로 이동 요청을 보낸다.
		//Redirect:재요청
		//컨텍스트루트를 받아와서 메인페이지로 보내기
		response.sendRedirect(request.getContextPath());
		
		
		
		
		
		
		
	}

}
