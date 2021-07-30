package com.myspring.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycgv.dao.BoardDAO;
import com.mycgv.vo.BoardVO;

@Service("boardService")
public class BoardServiceImpl2 implements BoardNoticeService {
	
	@Autowired
	private BoardDAO boardDAO;
	
	@Override
	public int getSelectDelete(String[] array) {
		return 0;
	}
	@Override
	public int execTotalCount() {
		return boardDAO.execTotalCount();
	}
	
	@Override
	public String getFile(String bid) {
		return boardDAO.getBsfile(bid);
	}
	
	@Override
	public boolean getDeleteResult(String bid) {
		return boardDAO.getDeleteResult(bid);
	}
	
	@Override
	public boolean getUpdateResultNofile(Object vo) {
		return boardDAO.getUpdateResultNofile((BoardVO)vo);
	}
	
	@Override
	public boolean getUpdateResult(Object vo) {
		return boardDAO.getUpdateResult((BoardVO)vo);
	}
	
	@Override
	public void getUpdateHit(String bid) {
		boardDAO.getUpdateHit(bid);
	}
	
	@Override
	public Object getContent(String bid) {
		return boardDAO.getContent(bid);
	}
	
	@Override
	public ArrayList<Object> getList(int start, int end){
		return boardDAO.getList(start, end);
	}
	
	@Override
	public boolean getInsertResult(Object vo) {
		return boardDAO.getInsertResult((BoardVO)vo);
	}
}
