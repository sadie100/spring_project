package com.myspring.service;

import java.util.ArrayList;

import com.mycgv.vo.MemberVO;
import com.mycgv.vo.SessionVO;

public interface MemberService {
	
	MemberVO getContent(String id);
	//ArrayList<MemberVO> getList();
	//boolean getLoginResult(String id, String pass);
	SessionVO getLoginResult(MemberVO vo);
	boolean getInsertResult(MemberVO vo);
	ArrayList<MemberVO> getList(int start, int end);
	int execTotalCount();
	int getIdCheck(String id);
	
}
