package com.heavenstar.zandi.persistance;

import java.util.List;

import com.heavenstar.zandi.model.GroupVO;

public interface GroupDao {
	
	public List<GroupVO> selectAll();
	public GroupVO findByGroup(int g_seq);
	public List<GroupVO> findByGroupPeople(String g_name);
	public int insert(GroupVO groupVO);
	public int insertPeople(GroupVO groupVO);
	public int update(GroupVO groupVO);
	public int delete(String g_name);
	public int deletePeople(String j_username);
	
	public void create_group_table();
	public void create_join_group_table();
}
