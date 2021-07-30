package com.myspring.service;

import java.util.List;

import com.mycgv.vo.BoardVO;

public interface BoardService {
	
	int execTotalCount();
	String getBsfile(String bid);
	boolean getDeleteResult(String bid);
	boolean getUpdateResultNofile(BoardVO vo);
	boolean getUpdateResult(BoardVO vo);
	void getUpdateHit(String bid);
	BoardVO getContent(String bid);
	List<Object> getList(int start, int end);
	boolean getInsertResult(BoardVO vo);

}
