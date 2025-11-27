package com.kh.mybatis.member.model.service;

import org.apache.ibatis.session.SqlSession;

import com.kh.mybatis.common.Template;
import com.kh.mybatis.member.model.dao.MemberDao;
import com.kh.mybatis.member.model.vo.Member;

//연결객체 담당 클래스
public class MemberService {
	
	//MemberDao 선언해놓고 사용하기
	private MemberDao dao = new MemberDao();
	
	//로그인 메서드
	public Member loginMember(Member m) {
		
		//SqlSession 받아오기(connection 역학)
		SqlSession sqlSession = Template.getSqlSession();
		
		//전달받은 데이터와 생성한 sqlSession을 함께 dao의 메서드에 전달하기
		Member loginMember = dao.loginMember(sqlSession,m);
		
		//select구문은 트랜잭션처리 필요 없으니 자원 반납하기
		sqlSession.close();
		
		return loginMember;
		
		
	}

	//회원가입 메서드
	public int insertMember(Member m) {
		
		//sqlSession과 함께 데이터 전달
		SqlSession sqlSession = Template.getSqlSession();
		
		int result = dao.insertMember(sqlSession,m);
		
		//DML구문이므로 트랜잭션 처리
		
		if(result>0) {
			sqlSession.commit();
		}else {
			sqlSession.rollback();
		}
		
		sqlSession.close();
		
		return result;
	}
	
	//업데이트 메서드
	public int updateMember(Member m) {
		
		SqlSession sqlSession = Template.getSqlSession();
		
		int result = dao.updateMember(sqlSession,m);
		
		//DML 구문이므로 트랜잭션
		
		if(result>0) {
			sqlSession.commit();
		}else {
			sqlSession.rollback();
		}
		
		sqlSession.close();
		
		return result;
	}
	//업데이트 후 정보 출력용 메서드
	public Member selectMember(Member m) {

		SqlSession sqlSession = Template.getSqlSession();
		
		//정보 select
		Member memberinfo = dao.selectMember(sqlSession,m);
		return memberinfo;
	}
	
	
	
	
	

}
