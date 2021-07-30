package com.myspring.service;

import java.util.ArrayList;

import com.mycgv.vo.NoticeVO;

public interface BoardNoticeService {
	int execTotalCount();
	String getFile(String id);
	boolean getDeleteResult(String id);
	boolean getUpdateResultNofile(Object vo);
	boolean getUpdateResult(Object vo);
	void getUpdateHit(String id);
	Object getContent(String id);
	ArrayList<Object> getList(int start, int end);
	boolean getInsertResult(Object vo);
	int getSelectDelete(String[] array);
}
