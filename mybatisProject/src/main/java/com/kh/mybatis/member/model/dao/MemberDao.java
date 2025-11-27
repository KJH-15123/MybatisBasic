package com.kh.mybatis.member.model.dao;

import org.apache.ibatis.session.SqlSession;

import com.kh.mybatis.member.model.vo.Member;

public class MemberDao {
	
	/*
		기존에 하던 방식대로 기본 생성자 구문에서 xml파일을 읽어올 필요 없이
		마이바티스에게 원하는 구문에 해당하는 메서드를 호출하면 된다.
		이때 매퍼(sql구문이 담긴 xml파일)의 이름(namespace)과 해당 구문에 키값(id)를 이용하여 요청한다.
		요청 메서드 : insert,update,delete,selectOne,selectList...
	*/
	
	public MemberDao() {
		
	}
	
	//sqlSession을 이용하여 마이바티스에게 명령하기
	public Member loginMember(SqlSession sqlSession,Member m) {
		
		//사용할 매퍼와 매퍼에 있는 sql구문의 id값을 이용하고 수행된 결과를 받는다.
		Member loginMember = sqlSession.selectOne("memberMapper.loginMember", m);
		
		return loginMember;//반환하기
		
	}
	//회원 추가
	public int insertMember(SqlSession sqlSession, Member m) {

		return sqlSession.insert("memberMapper.insertMember",m);
	}
	//회원정보 업데이트
	public int updateMember(SqlSession sqlSession, Member m) {
		return sqlSession.update("memberMapper.updateMember",m);
	}
	
	//회원정보 조회
	public Member selectMember(SqlSession sqlSession, Member m) {
		return sqlSession.selectOne("memberMapper.selectMember",m);
	}



}
