package com.heavenstar.zandi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.heavenstar.zandi.model.UserVO;
import com.heavenstar.zandi.persistance.UserDao;
import com.heavenstar.zandi.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;


	@Override
	public List<UserVO> selectAll() {
		// TODO Auto-generated method stub
		return userDao.selectAll();
	}

	@Override
	public UserVO findById(String username) {
		// TODO Auto-generated method stub
		return userDao.findById(username);
	}

	@Override
	public UserVO findByName(String nickname) {
		// TODO Auto-generated method stub
		return userDao.findByName(nickname);
	}
	@Override
	public int insert(UserVO userVO) {
		// TODO Auto-generated method stub
		userDao.insert(userVO);
		return 0;
	}

	@Override
	public int update(UserVO userVO) {
		// TODO Auto-generated method stub
		return userDao.update(userVO);
	}

	@Override
	public int delete(String username) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void create_user_table() {

	}


	


}
