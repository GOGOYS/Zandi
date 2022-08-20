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
	public GroupVO findByGroup(int g_seq) {
		// TODO Auto-generated method stub
		return groupDao.findByGroup(g_seq);
	}
	
	@Override
	public List<GroupVO> findByGroupPeople(String g_name) {
		// TODO Auto-generated method stub
		return groupDao.findByGroupPeople(g_name);
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
	public int update(GroupVO groupVO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(String g_name) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deletePeople(String j_username) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void create_group_table() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void create_join_group_table() {
		// TODO Auto-generated method stub
		
	}



}
