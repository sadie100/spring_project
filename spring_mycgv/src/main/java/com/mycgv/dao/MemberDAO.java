package com.mycgv.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mycgv.vo.MemberVO;
import com.mycgv.vo.SessionVO;

@Repository
public class MemberDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;	//1, 2단계 처리
	
	private static String namespace = "mapper.member";
	
	//select : r
	//로그인 처리 -- count 함수 사용
	public SessionVO getLoginResult(MemberVO vo) {
		System.out.println("--->> mybatis test ");
		return sqlSession.selectOne(namespace+".login", vo);
	}
		
	//id 중복체크
	public int getIdCheck(String id) {
		return sqlSession.selectOne(namespace+".idcheck",id);
	}
	
	
	/* 전체 카운트 가져오기*/
	//execTotalCount()
	public int execTotalCount(){
		return sqlSession.selectOne(namespace+".count");
		
	}
	
	
	//Select --> 전체 리스트 : 페이징 처리
		public ArrayList<MemberVO> getList(int start, int end){
			Map param = new HashMap<String, String>();
			//Map은 인터페이스이기에 그 자체로 생성할 수는 없다.
			param.put("start", start);
			param.put("end", end);
			List<MemberVO> list= sqlSession.selectList(namespace+".list", param);
			
			return (ArrayList<MemberVO>)list;
			
			
		}
		
	//회원 상세정보
	public MemberVO getContent(String id) {
		return sqlSession.selectOne(namespace+".content", id);
		
	}
	
	//회원가입
	public int getInsertResult(MemberVO vo) {
		return sqlSession.insert(namespace+".join", vo);
	}
		
	
	/*
	//insert : c --> 회원가입
	public boolean getInsertResult(String id, String pass, String name, String gender, String email, String hp, String hlist, String intro) {
		boolean result = false;
		//id, pass, name, gender, email, hp, hlist, intro, mdate
		String sql = "insert into mycgv_member values(?,?,?,?,?,?,?,?,sysdate,0)";
		getPreparedStatement(sql);
		
		try {
			pstmt.setString(1, id);
			pstmt.setString(2, pass);
			pstmt.setString(3, name);
			pstmt.setString(4, gender);
			pstmt.setString(5, email);
			pstmt.setString(6, hp);
			pstmt.setString(7, hlist);
			pstmt.setString(8, intro);
			
			int value = pstmt.executeUpdate();
			
			if(value != 0) {
				result = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return result;
	}

	
	
	public boolean getLoginResult(String id, String pass) {
		boolean result = false;
		String sql = "select count(*) from mycgv_member where id=? and pass=?";
		getPreparedStatement(sql);
		try {
			pstmt.setString(1, id);
			pstmt.setString(2, pass);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getInt(1)==1) result = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		//close();
		
		return result;
	}
	
	
	//회원 전체 리스트
	public ArrayList<MemberVO> getList(){
		ArrayList<MemberVO> list = new ArrayList<MemberVO>();
		String sql = " SELECT ROWNUM RNO, ID, NAME, HP, GENDER, TO_CHAR(MDATE,'YYYY-MM-DD') MDATE, CHOICE " + 
				" FROM (SELECT ID, NAME, HP, GENDER, MDATE, CHOICE FROM MYCGV_MEMBER " + 
				" ORDER BY MDATE DESC) ";
		getPreparedStatement(sql);
		
		try {
			rs = pstmt.executeQuery();
			while(rs.next()) {
				MemberVO vo = new MemberVO();
				vo.setRno(rs.getInt(1));
				vo.setId(rs.getString(2));
				vo.setName(rs.getString(3));
				vo.setHp(rs.getString(4));
				vo.setGender(rs.getString(5));
				vo.setMdate(rs.getString(6));
				vo.setChoice(rs.getInt(7));
				
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//close();
		return list;
	}
	*/
}
