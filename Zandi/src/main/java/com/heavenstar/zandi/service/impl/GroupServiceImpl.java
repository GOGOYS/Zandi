package com.heavenstar.zandi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.heavenstar.zandi.model.GroupVO;
import com.heavenstar.zandi.persistance.GroupDao;
import com.heavenstar.zandi.service.GroupService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class GroupServiceImpl implements GroupService{
	
	@Autowired
	private GroupDao groupDao;

	@Override
	public List<GroupVO> selectAll() {
		// TODO Auto-generated method stub
		return groupDao.selectAll();
	}

	@Override
	public GroupVO findByGroup(long g_seq) {
		// TODO Auto-generated method stub
		return groupDao.findByGroup(g_seq);
	}
	
	@Override
	public List<GroupVO> findByGroupPeople(String g_name) {
		// TODO Auto-generated method stub
		return groupDao.findByGroupPeople(g_name);
	}
	
	@Override
	public GroupVO findByOnePeople(String j_gname, String j_username) {
		// TODO Auto-generated method stub
		return groupDao.findByOnePeople(j_gname, j_username);
	}

	@Override
	public int insert(GroupVO groupVO) {
		// TODO Auto-generated method stub
		return groupDao.insert(groupVO);
	}

	@Override
	public int insertPeople(GroupVO groupVO) {
		// TODO Auto-generated method stub
		return groupDao.insertPeople(groupVO);
	}

	@Override
	public int updateCount(GroupVO groupVO) {
		// TODO Auto-generated method stub
		return groupDao.updateCount(groupVO);
	}
	@Override
	public int update(GroupVO groupVO) {
		// TODO Auto-generated method stub
		return groupDao.update(groupVO);
	}

	@Override
	public int delete(long g_seq) {
		// TODO Auto-generated method stub
		return groupDao.delete(g_seq);
	}

	@Override
	public int deletePeople(long j_seq) {
		// TODO Auto-generated method stub
		return groupDao.deletePeople(j_seq);
	}

	@Override
	public void create_group_table() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void create_join_group_table() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String percent(String create_date,int end_date, int percent) {
		
		//완료율 구하기
		//1.방이 생성된 날짜로부터 마지막 날짜까지의 남은기간 구하기
		//2. 남은 기간 동안의 출석환인률 구하기
		
		//남은 날짜 구하기?
		
		// TODO Auto-generated method stub
		return null;
	}




}
