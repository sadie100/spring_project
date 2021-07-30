package com.myspring.service;

import java.util.ArrayList;

import com.mycgv.dao.DBConn;
import com.mycgv.vo.NoticeVO;

public interface NoticeService {
	ArrayList<NoticeVO> getList(int start, int end);
	int execTotalCount();
	String getNsfile(String nid);
	boolean getDeleteResult(String nid);
	boolean getUpdateResultNofile(NoticeVO vo);
	boolean getUpdateResult(NoticeVO vo);
	void getUpdateHit(String nid);
	NoticeVO getContent(String nid);
	boolean getInsertResult(NoticeVO vo);
}
