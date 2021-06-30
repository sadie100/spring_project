package com.mycgv.dao;

import java.util.ArrayList;

import com.mycgv.vo.BoardVO;

public class BoardDAO extends DBConn{
	/* ��ü ī��Ʈ ��������*/
	//execTotalCount()
	public int execTotalCount(){
		int count = 0;
		String sql = " select count(*) from mycgv_board";
		getPreparedStatement(sql);
		
		try {
			rs = pstmt.executeQuery();
			if(rs.next()) count = rs.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return count;
	}
	
	//Delete ����� upload�� ���� ������ ���� ���ϸ� ��������
	public String getBsfile(String bid) {
		String bsfile = null;
		String sql = " select bsfile from mycgv_board where bid=? ";
		getPreparedStatement(sql);
		
		try {
			pstmt.setString(1, bid);
			rs = pstmt.executeQuery();
			if(rs.next()) bsfile = rs.getString(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return bsfile;
	}
	//Delete --> ���� ó��
	public boolean getDeleteResult(String bid) {
		boolean result = false;
		String sql = " delete from mycgv_board where bid=? ";
		getPreparedStatement(sql);
		
		try {
			pstmt.setString(1, bid);
			
			int value = pstmt.executeUpdate();
			if(value!=0) {
				result = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		close();
		return result;
	}
	
	//Update ---> ���� ó��(���� ������ ����)
	public boolean getUpdateResultNofile(BoardVO vo) {
		boolean result = false;
		String sql = "update mycgv_board set btitle=?, bcontent = ? where bid=?";
		getPreparedStatement(sql);
		
		try {
			pstmt.setString(1, vo.getBtitle());
			pstmt.setString(2, vo.getBcontent());
			pstmt.setString(3, vo.getBid());
			
			int value = pstmt.executeUpdate();
			if(value!=0) {
				result = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		close();
		return result;
	}
	//Update ---> ���� ó��(���ο� ���� ����)
	public boolean getUpdateResult(BoardVO vo) {
		boolean result = false;
		String sql = "update mycgv_board set btitle=?, bcontent = ?, bfile=?, bsfile=? where bid=?";
		getPreparedStatement(sql);
		
		try {
			pstmt.setString(1, vo.getBtitle());
			pstmt.setString(2, vo.getBcontent());
			pstmt.setString(3, vo.getBfile());
			pstmt.setString(4, vo.getBsfile());
			pstmt.setString(5, vo.getBid());
			
			int value = pstmt.executeUpdate();
			if(value!=0) {
				result = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		close();
		return result;
	}
	//Update ---> ��ȸ�� ������Ʈ
	public void getUpdateHit(String bid) {
		String sql = " update mycgv_board set bhit = bhit+1 where bid=? ";
		getPreparedStatement(sql);
		
		try {
			pstmt.setString(1, bid);
			pstmt.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		close();
	}
	
	//Select --> ������
	public BoardVO getContent(String bid) {
		BoardVO vo = new BoardVO();
		String sql = " select bid, btitle, bcontent, bhit, to_char(bdate, 'yyyy-mm-dd') bdate, bfile, bsfile " + 
				" from mycgv_board where bid=? ";
		getPreparedStatement(sql);
		
		try {
			pstmt.setString(1, bid);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				vo.setBid(rs.getString(1));
				vo.setBtitle(rs.getString(2));
				vo.setBcontent(rs.getString(3));
				vo.setBhit(rs.getInt(4));
				vo.setBdate(rs.getString(5));
				vo.setBfile(rs.getString(6));
				vo.setBsfile(rs.getString(7));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vo;
	}
	
	
	//Select --> ��ü ����Ʈ : ����¡ ó��
	public ArrayList<BoardVO> getList(int start, int end){
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		String sql = " select rno, bid, btitle, bhit, bdate" + 
				" from ( select rownum rno, bid, btitle, bhit, to_char(bdate, 'yyyy-mm-dd') bdate " + 
				"		from (select bid, btitle, bhit, bdate from mycgv_board " + 
				"		order by bdate desc)) " + 
				"    where rno between ? and ? ";
		
		getPreparedStatement(sql);
		
		try {
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			
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
		
		close();
		return list;
	}
	
	//Select --> ��ü ����Ʈ
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
		
		close();
		return list;
	}
	
	//insert ---> �Խ��� �۾���
	public boolean getInsertResult(BoardVO vo) {
		boolean result = false;
		String sql = "insert into mycgv_board values('b_'||sequ_mycgv_board.nextval,?,?,?,?,0,sysdate)";
		getPreparedStatement(sql);
		
		try {
			pstmt.setString(1, vo.getBtitle());
			pstmt.setString(2, vo.getBcontent());
			pstmt.setString(3, vo.getBfile());
			pstmt.setString(4, vo.getBsfile());
			
			int value = pstmt.executeUpdate();
			if(value!=0) {
				result = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		close();
		return result;
	}


}
