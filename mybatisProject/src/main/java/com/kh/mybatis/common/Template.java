package com.kh.mybatis.common;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

//마이바티스에게 명령을 내릴때 필요한 sqlSession 객체 준비하기
public class Template {
	
	public static SqlSession getSqlSession() {
		
		//mybatis-config 에서 설정한 db정보를 이용하여 sqlSession 객체를 생성하고 반환받기
		
		/*
		- 공식 홈페이지 예시 코드
		String resource = "org/mybatis/example/mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		*/
		
		SqlSession sqlSession = null;
		// /는  classes폴더(컴파일 된 최상위 폴더)
		String resource = "/resources/mybatis-config.xml";
		
		//입력한 config파일 경로로 연결하는 inputStream을 얻어오기
		try (
			//입력한 config파일 경로로 연결하는 inputStream을 얻어오기
			//자동 자원반납도 설정
			InputStream inputStream = Resources.getResourceAsStream(resource)
			) {
			
			//얻어온 inputStream을 이용하여 SqlSeccionFactory 생성하기
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			
			//SqlSeccionFactory에 사용할 SqlSession 객체 받아오기
			//openSession(true/false) : autoCommit 설정 - false면 해제
			sqlSession = sqlSessionFactory.openSession(false);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return sqlSession;
		
	}

}
