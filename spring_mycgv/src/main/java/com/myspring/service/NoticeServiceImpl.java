package com.myspring.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.mycgv.dao.NoticeDAO;
import com.mycgv.vo.NoticeVO;

public class NoticeServiceImpl implements NoticeService{
	
	@Autowired
	private NoticeDAO noticeDAO;
	
	@Override
	public String getNsfile(String nid) {
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
	public boolean getUpdateResultNofile(NoticeVO vo) {
		return noticeDAO.getUpdateResultNofile(vo);
	}
	
	@Override
	public boolean getUpdateResult(NoticeVO vo) {
		return noticeDAO.getUpdateResult(vo);
	}
	
	@Override
	public void getUpdateHit(String nid) {
		noticeDAO.getUpdateHit(nid);
	}
	
	@Override
	public NoticeVO getContent(String nid) {
		return noticeDAO.getContent(nid);
	}
	
	@Override
	public boolean getInsertResult(NoticeVO vo) {
		return noticeDAO.getInsertResult(vo);
	}
	
	@Override
	public ArrayList<NoticeVO> getList(int start, int end){
		return noticeDAO.getList(start, end);
	}
}
