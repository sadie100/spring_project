package com.mycgv.comms;

import java.util.HashMap;

import com.myspring.service.BoardNoticeService;
import com.myspring.service.MemberService;

public class Commons {
	//페이징 처리 메소드 - startCount, endCount : HashMap map = commons.getPage(rpage, dao);
	public HashMap<String,Integer> getPage(String rpage, Object obj, String name) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		
		//페이징 처리 - startCount, endCount 구하기
		int startCount = 0;
		int endCount = 0;
		int pageSize = 5;	//한페이지당 게시물 수
		int reqPage = 1;	//요청페이지	
		int pageCount = 1;	//전체 페이지 수
		int dbCount = 0;
		if(name.equals("board")) {
			BoardNoticeService boardService = (BoardNoticeService)obj;
			dbCount = boardService.execTotalCount();	//DB에서 가져온 전체 행수 : 5 
		}else if(name.equals("member")) {
			MemberService memberService = (MemberService)obj;
			dbCount = memberService.execTotalCount();	//DB에서 가져온 전체 행수 : 5 
		}else if(name.equals("notice")) {
			BoardNoticeService noticeService = (BoardNoticeService)obj;
			dbCount = noticeService.execTotalCount();	//DB에서 가져온 전체 행수 : 5 
		}

		//총 페이지 수 계산. pagecount : 현재 페이지
		if(dbCount % pageSize == 0){
			pageCount = dbCount/pageSize;
		}else{
			pageCount = dbCount/pageSize+1;
		}

		//요청 페이지 계산
		if(rpage != null){
			reqPage = Integer.parseInt(rpage);
			startCount = (reqPage-1) * pageSize+1;
			endCount = reqPage *pageSize;
		}else{
			startCount = 1;
			endCount = 5;
		}
		
		map.put("start", startCount);
		map.put("end", endCount);
		map.put("dbCount",dbCount);
		map.put("pageSize",pageSize);
		map.put("rpage",reqPage);
		return map;
	}
}
