package com.kh.mybatis.board.model.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.kh.mybatis.board.model.vo.Board;
import com.kh.mybatis.common.model.vo.PageInfo;

public class BoardDao {
	
	
	//게시글 개수 
	public int listCount(SqlSession sqlSession) {
		
		return sqlSession.selectOne("boardMapper.listCount");
	}
	
	/*
	//게시글 목록 조회 rownum 이용하여 직접 처리 하는 방법 
	public ArrayList<Board> selectList(SqlSession sqlSession, PageInfo pi) {
		//페이징 처리 조회 방법
		//1)직접 게시글 몇번부터 보여줄것인지 지정하는 방법 
		//현재 페이지에 따라서 보여줘야하는 게시글 번호가 다르기 때문에 계산처리 해야함
		//currentPage = 1 | boardLimit = 5    ==  게시글은 1~5 번 
		//currentPage = 2 | boardLimit = 5    ==  게시글은 6~10 번  
		//currentPage = 3 | boardLimit = 5    ==  게시글은 11~15 번
		HashMap<String,Integer> bno = new HashMap<>();
		
		//시작번호
		int start = (pi.getCurrentPage()-1)*pi.getBoardLimit()+1;
		//끝번호
		int end = pi.getCurrentPage()*pi.getBoardLimit();
		
		bno.put("start", start);
		bno.put("end", end);
		
		
		
		return (ArrayList)sqlSession.selectList("boardMapper.selectList",bno);
	}
	
	*/
	
	//2)마이바티스에서 제공하는 객체 rowBounds 이용하기 
	public ArrayList<Board> selectList(SqlSession sqlSession, PageInfo pi) {
		
		//방법2) 마이바티스에서 제공하는 rowbounds 객체 이용하기 
		//RowBounds 객체에 매개변수 offset과 limit을 전달한다.
		//offset : 몇개씩 건너뛰고 보여줄것인지에 대한 값
		//limit : 몇개씩 보여줄것인지에 대한 값 - boardLimit 과 같은 값 
		
		//한 페이지에 5개씩 게시글을 보여주기 
		//1페이지 : 1~5 / 건너뛸값 없으니 0
		//2페이지 : 6~10 / 6번부터 보여줘야하니 건너뛸값 5 
		//3페이지 : 11~15 / 11번부터 보여줘야하니 건너뛸값 10
		
		int limit = pi.getBoardLimit();
		int offset = (pi.getCurrentPage()-1)*limit; //현재페이지-1 * 몇개씩 보여줄것인지
		
		RowBounds rowBounds = new RowBounds(offset,limit);
		
		//전달할 파라미터가 없어도 메소드의 매개변수 위치가 정해져있기 때문에 형식을 유지하기위해 null을 전달한다. 
		//selectList("Mapper.--",매개변수,페이징 처리 정보)
		ArrayList<Board> list = (ArrayList)sqlSession.selectList("boardMapper.selectList",null,rowBounds);
		
		return list;
	}
	
	
	//조회수 증가 
	public int increaseCount(SqlSession sqlSession, int bno) {
		
		return sqlSession.update("boardMapper.increaseCount",bno);
	}
	
	//게시글 상세조회
	public Board selectBoard(SqlSession sqlSession, int bno) {
		
		return sqlSession.selectOne("boardMapper.selectBoard",bno);
	}
	
	//게시글 등록
	public int insertBoard(SqlSession sqlSession, Board b) {
		
		return sqlSession.insert("boardMapper.insertBoard",b);
	}
	
	
	
	
	
	
	
	
	
	
	

}
