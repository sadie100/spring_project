package com.mycgv.dao;

import java.util.ArrayList;

import com.mycgv.vo.MemberVO;
import com.mycgv.vo.SessionVO;

public class MemberDAO extends DBConn{
	
	//id 중복체크
	public int getIdCheck(String id) {
		int result = 0;
		String sql = "select count(*) from mycgv_member where id=?";
		
		getPreparedStatement(sql);
		
		try {
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	/* 전체 카운트 가져오기*/
	//execTotalCount()
	public int execTotalCount(){
		int count = 0;
		String sql = " select count(*) from mycgv_member";
		getPreparedStatement(sql);
		
		try {
			rs = pstmt.executeQuery();
			if(rs.next()) count = rs.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return count;
	}
	
	
	//Select --> 전체 리스트 : 페이징 처리
		public ArrayList<MemberVO> getList(int start, int end){
			ArrayList<MemberVO> list = new ArrayList<MemberVO>();
			String sql = "select rno, id, name, hp, gender, mdate, choice "
					+ " from (SELECT ROWNUM RNO, ID, NAME, HP, GENDER, TO_CHAR(MDATE,'YYYY-MM-DD') MDATE, CHOICE " + 
					"	 FROM (SELECT ID, NAME, HP, GENDER, MDATE, CHOICE FROM MYCGV_MEMBER " + 
					"	ORDER BY MDATE DESC))"
					+ " where rno between ? and ? ";
			
			getPreparedStatement(sql);
			
			try {
				pstmt.setInt(1, start);
				pstmt.setInt(2, end);
				
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
			
			close();
			return list;
		}
		
		
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

	public boolean getInsertResult(MemberVO vo) {
		boolean result = false;
		String sql = "insert into mycgv_member values(?,?,?,?,?,?,?,?,sysdate,0)";
		getPreparedStatement(sql);
		
		try {
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPass());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getGender());
			pstmt.setString(5, vo.getEmail());
			pstmt.setString(6, vo.getHp());
			pstmt.setString(7, vo.getHlist());
			pstmt.setString(8, vo.getIntro());
			
			int value = pstmt.executeUpdate();
			
			if(value != 0) {
				result = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		close();
		return result;
	}
	
	//select : r
	//로그인 처리 -- count 함수 사용
	public SessionVO getLoginResult(MemberVO vo) {
		SessionVO svo = new SessionVO();
		String sql = "select count(*) result, name from mycgv_member where id=? and pass=? group by name ";
		getPreparedStatement(sql);
		try {
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPass());
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				//if(rs.getInt(1)==1) result = true;
				svo.setResult(rs.getInt(1));
				svo.setName(rs.getString(2));
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		close();
		
		return svo;
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
		close();
		
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
		close();
		return list;
	}
	
	//회원 상세정보
	public MemberVO getContent(String id) {
		MemberVO vo = new MemberVO();
		String sql = " select ID, NAME, HP, GENDER, email, TO_CHAR(MDATE,'YYYY-MM-DD') MDATE, hlist, intro " + 
				" from mycgv_member where id=? ";
		getPreparedStatement(sql);
		
		try {
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				vo.setId(rs.getString(1));
				vo.setName(rs.getString(2));
				vo.setHp(rs.getString(3));
				vo.setGender(rs.getString(4));
				vo.setEmail(rs.getString(5));
				vo.setMdate(rs.getString(6));
				vo.setHlist(rs.getString(7));
				vo.setIntro(rs.getString(8));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		close();
		return vo;
	}
	
	//update : u
	
	//delete : d
	
	
	
}
