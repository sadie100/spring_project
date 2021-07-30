package com.myspring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycgv.dao.BoardDAO;
import com.mycgv.vo.BoardVO;

@Service("boardService")
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardDAO boardDAO;
	
	@Override
	public int execTotalCount() {
		return boardDAO.execTotalCount();
	}
	
	@Override
	public String getBsfile(String bid) {
		return boardDAO.getBsfile(bid);
	}
	
	@Override
	public boolean getDeleteResult(String bid) {
		return boardDAO.getDeleteResult(bid);
	}
	
	@Override
	public boolean getUpdateResultNofile(BoardVO vo) {
		return boardDAO.getUpdateResultNofile(vo);
	}
	
	@Override
	public boolean getUpdateResult(BoardVO vo) {
		return boardDAO.getUpdateResult(vo);
	}
	
	@Override
	public void getUpdateHit(String bid) {
		boardDAO.getUpdateHit(bid);
	}
	
	@Override
	public BoardVO getContent(String bid) {
		return boardDAO.getContent(bid);
	}
	
	@Override
	public List<Object> getList(int start, int end){
		List<Object> list = boardDAO.getList(start, end);
		return list;
	}
	
	@Override
	public boolean getInsertResult(BoardVO vo) {
		return boardDAO.getInsertResult(vo);
	}
}
