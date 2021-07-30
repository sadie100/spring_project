package com.myspring.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycgv.dao.MemberDAO;
import com.mycgv.vo.MemberVO;
import com.mycgv.vo.SessionVO;

@Service("memberService")
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	private MemberDAO memberDAO;
	
	
	@Override	//필수 Annotation은 아님. 다만 개발 편의를 위해 적는 문구
	public MemberVO getContent(String id) {
		return memberDAO.getContent(id);
	}
	
	
	@Override
	public SessionVO getLoginResult(MemberVO vo) {
		return memberDAO.getLoginResult(vo);
	}
	
	@Override
	public boolean getInsertResult(MemberVO vo) {
		boolean result = false;
		int value= memberDAO.getInsertResult(vo);
		if(value!=0) result = true;
		return result;
	}
	
	@Override
	public ArrayList<MemberVO> getList(int start, int end){
		return memberDAO.getList(start, end);
	}
	
	@Override
	public int execTotalCount() {
		return memberDAO.execTotalCount();
	}
	
	@Override
	public int getIdCheck(String id) {
		return memberDAO.getIdCheck(id);
	}
}
