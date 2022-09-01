package com.heavenstar.zandi.service;

import com.heavenstar.zandi.persistance.GroupDao;

public interface GroupService extends GroupDao{
	
	
	//날짜비교해서 완료율 체크하기
	public String percent(String create_date,int end_date, int percent);
	

}
