package com.heavenstar.zandi.service;

import java.text.ParseException;

import com.heavenstar.zandi.persistance.GroupDao;

public interface GroupService extends GroupDao{
	
	
	//시작일과 끝나는일 차이 구하기
	public int periodCheck(String create_date,String end_date)throws ParseException;
	

}
