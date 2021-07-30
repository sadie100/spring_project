package com.mycgv.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mycgv.vo.BoardVO;

@Repository
public class BoardDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	private String namespace = "mapper.board";
	
	
	/* ��ü ī��Ʈ ��������*/
	//execTotalCount()
	public int execTotalCount(){
		return sqlSession.selectOne(namespace+".count");
	}
	
	//Delete ����� upload�� ���� ������ ���� ���ϸ� ��������
	public String getBsfile(String bid) {
		return sqlSession.selectOne(namespace+".bsfile",bid);
	}
	
	//Delete --> ���� ó��
	public boolean getDeleteResult(String bid) {
		boolean result = false;
		int value=sqlSession.delete(namespace+".delete",bid);
		if(value!=0) result=true;
		return result;
	}
	
	//Update ---> ���� ó��(���� ������ ����)
	public boolean getUpdateResultNofile(BoardVO vo) {
		boolean result = false;
		int value = sqlSession.update(namespace+".updatenofile", vo);
		if(value!=0) result=true;
		return result;
	}
	
	//Update ---> ���� ó��(���ο� ���� ����)
	public boolean getUpdateResult(BoardVO vo) {
		boolean result = false;
		int value = sqlSession.update(namespace+".updatefile", vo);
		if(value!=0) result=true;
		return result;
	}
	
	//Update ---> ��ȸ�� ������Ʈ
	public void getUpdateHit(String bid) {
		sqlSession.update(namespace+".updatehit", bid);
		
	}
	
	//Select --> ������
	public BoardVO getContent(String bid) {
		return sqlSession.selectOne(namespace+".content", bid);
	}
	
	
	//Select --> ��ü ����Ʈ : ����¡ ó��
	public ArrayList<Object> getList(int start, int end){
		Map<String, String> param  = new HashMap<String, String>();
		param.put("start", String.valueOf(start));
		param.put("end", String.valueOf(end));
		List<Object> list=sqlSession.selectList(namespace+".list",param);
		
		return (ArrayList<Object>)list;
		
	}
	
	//Select --> ��ü ����Ʈ
	/*
	public ArrayList<BoardVO> getList(){
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		String sql = " select rownum rno, bid, btitle, bhit, to_char(bdate, 'yyyy-mm-dd') bdate " + 
				" from (select bid, btitle, bhit, bdate from mycgv_board " + 
				" order by bdate desc)";
		
		getPreparedStatement(sql);
		
		try {
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BoardVO vo = new BoardVO();
				vo.setRno(rs.getInt(1));
				vo.setBid(rs.getString(2));
				vo.setBtitle(rs.getString(3));
				vo.setBhit(rs.getInt(4));
				vo.setBdate(rs.getString(5));
	
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//close();
		return list;
	}
	*/
	
	//insert ---> �Խ��� �۾���
	public boolean getInsertResult(BoardVO vo) {
		boolean result = false;
		int value= sqlSession.insert(namespace+".insert", vo);
		if(value!=0) result = true;
		return result;
	}


}
