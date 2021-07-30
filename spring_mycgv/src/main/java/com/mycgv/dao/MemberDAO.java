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
	private SqlSessionTemplate sqlSession;	//1, 2�ܰ� ó��
	
	private static String namespace = "mapper.member";
	
	//select : r
	//�α��� ó�� -- count �Լ� ���
	public SessionVO getLoginResult(MemberVO vo) {
		System.out.println("--->> mybatis test ");
		return sqlSession.selectOne(namespace+".login", vo);
	}
		
	//id �ߺ�üũ
	public int getIdCheck(String id) {
		return sqlSession.selectOne(namespace+".idcheck",id);
	}
	
	
	/* ��ü ī��Ʈ ��������*/
	//execTotalCount()
	public int execTotalCount(){
		return sqlSession.selectOne(namespace+".count");
		
	}
	
	
	//Select --> ��ü ����Ʈ : ����¡ ó��
		public ArrayList<MemberVO> getList(int start, int end){
			Map param = new HashMap<String, String>();
			//Map�� �������̽��̱⿡ �� ��ü�� ������ ���� ����.
			param.put("start", start);
			param.put("end", end);
			List<MemberVO> list= sqlSession.selectList(namespace+".list", param);
			
			return (ArrayList<MemberVO>)list;
			
			
		}
		
	//ȸ�� ������
	public MemberVO getContent(String id) {
		return sqlSession.selectOne(namespace+".content", id);
		
	}
	
	//ȸ������
	public int getInsertResult(MemberVO vo) {
		return sqlSession.insert(namespace+".join", vo);
	}
		
	
	/*
	//insert : c --> ȸ������
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
	
	
	//ȸ�� ��ü ����Ʈ
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
