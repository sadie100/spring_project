package com.mycgv.comms;

import java.util.HashMap;

import com.mycgv.dao.BoardDAO;
import com.mycgv.dao.MemberDAO;
import com.mycgv.dao.NoticeDAO;

public class Commons {
	//����¡ ó�� �޼ҵ� - startCount, endCount : HashMap map = commons.getPage(rpage, dao);
	public HashMap<String,Integer> getPage(String rpage, Object obj, String name) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		
		//����¡ ó�� - startCount, endCount ���ϱ�
		int startCount = 0;
		int endCount = 0;
		int pageSize = 5;	//���������� �Խù� ��
		int reqPage = 1;	//��û������	
		int pageCount = 1;	//��ü ������ ��
		int dbCount = 0;
		if(name.equals("board")) {
			BoardDAO dao = (BoardDAO)obj;
			dbCount = dao.execTotalCount();	//DB���� ������ ��ü ��� : 5 
		}else if(name.equals("member")) {
			MemberDAO dao = (MemberDAO)obj;
			dbCount = dao.execTotalCount();	//DB���� ������ ��ü ��� : 5 
		}else if(name.equals("notice")) {
			NoticeDAO dao = (NoticeDAO)obj;
			dbCount = dao.execTotalCount();	//DB���� ������ ��ü ��� : 5 
		}

		//�� ������ �� ���. pagecount : ���� ������
		if(dbCount % pageSize == 0){
			pageCount = dbCount/pageSize;
		}else{
			pageCount = dbCount/pageSize+1;
		}

		//��û ������ ���
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
