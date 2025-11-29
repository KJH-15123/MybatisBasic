package com.kh.mybatis.board.model.service;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;

import com.kh.mybatis.board.model.dao.BoardDao;
import com.kh.mybatis.board.model.vo.Board;
import com.kh.mybatis.common.Template;
import com.kh.mybatis.common.model.vo.PageInfo;

public class BoardService {
	
	private BoardDao dao = new BoardDao();
	
	//게시글 총 개수 
	public int listCount() {
		
		SqlSession sqlSession = Template.getSqlSession();
		
		int listCount = dao.listCount(sqlSession);
		
		sqlSession.close();
		
		return listCount;
	}
	//게시글 목록 조회
	public ArrayList<Board> selectList(PageInfo pi) {
		
		SqlSession sqlSession = Template.getSqlSession();
		
		ArrayList<Board> list = dao.selectList(sqlSession,pi);
		
		sqlSession.close();
		
		return list;
	}
	
	//조회수 증가 메소드
	public int increaseCount(int bno) {
		
		SqlSession sqlSession = Template.getSqlSession();
		
		int result = dao.increaseCount(sqlSession,bno);
		
		//UPDATE 구문 트랜잭션 처리하기
		if(result>0) {
			sqlSession.commit();
		}else {
			sqlSession.rollback();
		}

		sqlSession.close();
		
		return result;
	}
	
	//게시글 조회 메소드
	public Board selectBoard(int bno) {
		SqlSession sqlSession = Template.getSqlSession();
		
		Board b = dao.selectBoard(sqlSession, bno);
		
		sqlSession.close();
		
		return b;
	}
	
	//게시글 등록
	public int insertBoard(Board b) {
		
		SqlSession sqlSession = Template.getSqlSession();
		
		int result = dao.insertBoard(sqlSession,b);
		
		//insert(DML)
		if(result>0) {
			sqlSession.commit();
		}else {
			sqlSession.rollback();
		}
		
		sqlSession.close();
		
		return result;
	}
	
	
	
	
	
	
	
	

}
