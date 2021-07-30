package com.mycgv.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mycgv.vo.NoticeVO;

@Repository
public class NoticeDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	private String namespace = "mapper.notice";
	
	//관리자 리스트 선택삭제 처리
	public int getSelectDelete(String[] stArray) {
		return sqlSession.delete(namespace+".select_delete", stArray);
	}
	
	//select ---> 공지사항 전체리스트
	public ArrayList<Object> getList(int start, int end){
		Map param = new HashMap<String, String>();
		param.put("start", start);
		param.put("end",end);

		List<Object> list = sqlSession.selectList(namespace+".list", param);
		return (ArrayList<Object>)list;
		
	}
	
	//page - 전체 행수
	public int execTotalCount() {
		return sqlSession.selectOne(namespace+".count");
	}
	
	
	//Delete 진행시 upload된 파일 삭제를 위해 파일명 가져오기
	public String getNsfile(String nid) {
		return sqlSession.selectOne(namespace+".nsfile", nid);
	}
		
	//delete ---> 공지사항 삭제
	public boolean getDeleteResult(String nid) {
		boolean result = false;
		int value = sqlSession.delete(namespace+".delete",nid);
		if(value!=0) result = true;
		
		return result;
	}

	//update ---> 공지사항 업데이트 : 기존 파일 유지
	public boolean getUpdateResultNofile(NoticeVO vo) {
		boolean result = false;
		int value = sqlSession.update(namespace+".updatenofile", vo);
		if(value!=0) result = true;

		return result;
	}
	
	//update ---> 공지사항 업데이트 : 새 파일 선택
	public boolean getUpdateResult(NoticeVO vo) {
		boolean result = false;
		int value = sqlSession.update(namespace+".updatefile", vo);
		if(value!=0) result = true;

		return result;
	}
	
	//Update ---> 공지사항 조회수 업데이트
	public void getUpdateHit(String nid) {
		sqlSession.update(namespace+".updatehit", nid);
	}
	
	//select ==> 공지사항 상세정보
	public NoticeVO getContent(String nid){
		return sqlSession.selectOne(namespace+".content", nid);
	}
		
		
	//insert --> 공지사항 글쓰기
	public boolean getInsertResult(NoticeVO vo) {
		boolean result = false;
		int value = sqlSession.insert(namespace+".insert", vo);
		if(value!=0) result=true;
		
		return result;
	}
	
	/*
	//select ==> 공지사항 전체화면
	public ArrayList<NoticeVO> getList(){
		ArrayList<NoticeVO> list = new ArrayList<NoticeVO>();
		String sql = " select rownum rno, nid, ntitle, ncontent, nfile, nsfile, nhit, to_char(ndate,'yyyy-mm-dd') ndate from " + 
				" (select nid, ntitle, ncontent, nfile, nsfile, nhit, ndate from mycgv_notice order by ndate desc) ";
		
		getPreparedStatement(sql);
		
		try {
			rs = pstmt.executeQuery();
			while(rs.next()) {
				NoticeVO vo = new NoticeVO();
				vo.setRno(rs.getInt(1));
				vo.setNid(rs.getString(2));
				vo.setNtitle(rs.getString(3));
				vo.setNcontent(rs.getString(4));
				vo.setNfile(rs.getString(5));
				vo.setNsfile(rs.getString(6));
				vo.setNhit(rs.getInt(7));
				vo.setNdate(rs.getString(8));
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
