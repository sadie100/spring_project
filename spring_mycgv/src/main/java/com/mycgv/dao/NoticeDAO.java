package com.mycgv.dao;

import java.util.ArrayList;

import com.mycgv.vo.NoticeVO;

public class NoticeDAO extends DBConn {
	//page - ���ϴ� ��� �������� �װ�
	public ArrayList<NoticeVO> getList(int start, int end){
		ArrayList<NoticeVO> list = new ArrayList<NoticeVO>();
		String sql = " select rno, nid, ntitle, ncontent, nfile, nsfile, nhit, ndate "
				+ " from (select rownum rno, nid, ntitle, ncontent, nfile, nsfile, nhit, to_char(ndate,'yyyy-mm-dd') ndate from " + 
				" (select nid, ntitle, ncontent, nfile, nsfile, nhit, ndate from mycgv_notice order by ndate desc)) "
				+ " where rno between ? and ? ";
		
		getPreparedStatement(sql);
		try {
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
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
		return list;
	}
	
	//page - ��ü ���
	public int execTotalCount() {
		String sql = " select count(*) from mycgv_notice ";
		int result =0;
		try {
			getPreparedStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			result = rs.getInt(1);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	//Delete ����� upload�� ���� ������ ���� ���ϸ� ��������
		public String getNsfile(String nid) {
			String nsfile = null;
			String sql = " select nsfile from mycgv_notice where nid=? ";
			getPreparedStatement(sql);
			
			try {
				pstmt.setString(1, nid);
				rs = pstmt.executeQuery();
				if(rs.next()) nsfile = rs.getString(1);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return nsfile;
		}
		
	//delete ---> �������� ����
	public boolean getDeleteResult(String nid) {
		boolean result = false;
		String sql = " delete from mycgv_notice where nid=? ";
		getPreparedStatement(sql);
		
		try {
			pstmt.setString(1, nid);
			
			int value= pstmt.executeUpdate();
			if(value!=0) result = true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		close();
		return result;
	}

	//update ---> �������� ������Ʈ : ���� ���� ����
	public boolean getUpdateResultNofile(NoticeVO vo) {
		boolean result = false;
		String sql = "update mycgv_notice set ntitle=?, ncontent=? where nid=? ";
		getPreparedStatement(sql);
		
		try {
			pstmt.setString(1, vo.getNtitle());
			pstmt.setString(2, vo.getNcontent());
			pstmt.setString(3, vo.getNid());

			int value= pstmt.executeUpdate();
			if(value!=0) result = true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		close();
		return result;
	}
	//update ---> �������� ������Ʈ : �� ���� ����
	public boolean getUpdateResult(NoticeVO vo) {
		boolean result = false;
		String sql = "update mycgv_notice set ntitle=?, ncontent=?, nfile=?, nsfile=? where nid=? ";
		getPreparedStatement(sql);
		
		try {
			pstmt.setString(1, vo.getNtitle());
			pstmt.setString(2, vo.getNcontent());
			pstmt.setString(3, vo.getNfile());
			pstmt.setString(4, vo.getNsfile());
			pstmt.setString(5, vo.getNid());
			
			int value= pstmt.executeUpdate();
			if(value!=0) result = true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		close();
		return result;
	}
	
	//Update ---> �������� ��ȸ�� ������Ʈ
	public void getUpdateHit(String nid) {
		String sql = "update mycgv_notice set nhit = nhit+1 where nid= ? ";
		getPreparedStatement(sql);
		try {
			pstmt.setString(1, nid);
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		close();
	}
	//select ==> �������� ������
	public NoticeVO getContent(String nid){
		NoticeVO vo = new NoticeVO();
		String sql = " select nid, ntitle, ncontent, nfile, nsfile, nhit, to_char(ndate,'yyyy-mm-dd') ndate " + 
				" from mycgv_notice where nid=? ";
		
		getPreparedStatement(sql);
		
		try {
			pstmt.setString(1, nid);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				vo.setNid(rs.getString(1));
				vo.setNtitle(rs.getString(2));
				vo.setNcontent(rs.getString(3));
				vo.setNfile(rs.getString(4));
				vo.setNsfile(rs.getString(5));
				vo.setNhit(rs.getInt(6));
				vo.setNdate(rs.getString(7));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vo;
	}
		
		
	//insert --> �������� �۾���
	public boolean getInsertResult(NoticeVO vo) {
		boolean result = false;
		String sql = " insert into mycgv_notice values('n_'||sequ_mycgv_notice.nextval, ?,?,?,?,0,sysdate) ";
		getPreparedStatement(sql);
		try {
			pstmt.setString(1, vo.getNtitle());
			pstmt.setString(2, vo.getNcontent());
			pstmt.setString(3, vo.getNfile());
			pstmt.setString(4, vo.getNsfile());
			
			int value = pstmt.executeUpdate();
			if(value!=0) {
				result = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		close();
		return result;
	}
	
	//select ==> �������� ��üȭ��
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
		close();
		return list;
	}
}
