package com.heavenstar.zandi.persistance;

import java.util.List;

import com.heavenstar.zandi.model.UserVO;

public interface UserDao {
	
	public List<UserVO> selectAll();
	public UserVO findById(String username);
	public UserVO findByName(String nickname);
	public int insert(UserVO userVO);
	public int update(UserVO userVO);
	public int delete(String username);
	
	public void create_user_table();

}
