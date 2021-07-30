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
	
	
	/* 전체 카운트 가져오기*/
	//execTotalCount()
	public int execTotalCount(){
		return sqlSession.selectOne(namespace+".count");
	}
	
	//Delete 진행시 upload된 파일 삭제를 위해 파일명 가져오기
	public String getBsfile(String bid) {
		return sqlSession.selectOne(namespace+".bsfile",bid);
	}
	
	//Delete --> 삭제 처리
	public boolean getDeleteResult(String bid) {
		boolean result = false;
		int value=sqlSession.delete(namespace+".delete",bid);
		if(value!=0) result=true;
		return result;
	}
	
	//Update ---> 수정 처리(기존 파일을 유지)
	public boolean getUpdateResultNofile(BoardVO vo) {
		boolean result = false;
		int value = sqlSession.update(namespace+".updatenofile", vo);
		if(value!=0) result=true;
		return result;
	}
	
	//Update ---> 수정 처리(새로운 파일 선택)
	public boolean getUpdateResult(BoardVO vo) {
		boolean result = false;
		int value = sqlSession.update(namespace+".updatefile", vo);
		if(value!=0) result=true;
		return result;
	}
	
	//Update ---> 조회수 업데이트
	public void getUpdateHit(String bid) {
		sqlSession.update(namespace+".updatehit", bid);
		
	}
	
	//Select --> 상세정보
	public BoardVO getContent(String bid) {
		return sqlSession.selectOne(namespace+".content", bid);
	}
	
	
	//Select --> 전체 리스트 : 페이징 처리
	public ArrayList<Object> getList(int start, int end){
		Map<String, String> param  = new HashMap<String, String>();
		param.put("start", String.valueOf(start));
		param.put("end", String.valueOf(end));
		List<Object> list=sqlSession.selectList(namespace+".list",param);
		
		return (ArrayList<Object>)list;
		
	}
	
	//Select --> 전체 리스트
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
	
	//insert ---> 게시판 글쓰기
	public boolean getInsertResult(BoardVO vo) {
		boolean result = false;
		int value= sqlSession.insert(namespace+".insert", vo);
		if(value!=0) result = true;
		return result;
	}


}
