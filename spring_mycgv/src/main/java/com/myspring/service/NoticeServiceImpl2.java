package com.myspring.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.mycgv.dao.NoticeDAO;
import com.mycgv.vo.NoticeVO;

public class NoticeServiceImpl2 implements BoardNoticeService{
	
	@Autowired
	private NoticeDAO noticeDAO;
	
	@Override
	public int getSelectDelete(String[] stArray) {
		return noticeDAO.getSelectDelete(stArray);
	}
	
	@Override
	public String getFile(String nid) {
		return noticeDAO.getNsfile(nid);
	}
	
	@Override
	public int execTotalCount() {
		return noticeDAO.execTotalCount();
	}
	
	@Override
	public boolean getDeleteResult(String nid) {
		return noticeDAO.getDeleteResult(nid);
	}
	
	@Override
	public boolean getUpdateResultNofile(Object vo) {
		return noticeDAO.getUpdateResultNofile((NoticeVO)vo);
	}
	
	@Override
	public boolean getUpdateResult(Object vo) {
		return noticeDAO.getUpdateResult((NoticeVO)vo);
	}
	
	@Override
	public void getUpdateHit(String nid) {
		noticeDAO.getUpdateHit(nid);
	}
	
	@Override
	public Object getContent(String nid) {
		return noticeDAO.getContent(nid);
	}
	
	@Override
	public boolean getInsertResult(Object vo) {
		return noticeDAO.getInsertResult((NoticeVO)vo);
	}
	
	@Override
	public ArrayList<Object> getList(int start, int end){
		return noticeDAO.getList(start, end);
	}
}
