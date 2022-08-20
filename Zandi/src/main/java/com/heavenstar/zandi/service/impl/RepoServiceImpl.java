package com.heavenstar.zandi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.heavenstar.zandi.model.RepoVO;
import com.heavenstar.zandi.persistance.RepoDao;
import com.heavenstar.zandi.service.RepoService;

@Service
public class RepoServiceImpl implements RepoService{
	
	@Autowired
	private RepoDao repoDao;

	@Override
	public List<RepoVO> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RepoVO> findByRepo(String r_username) {
		// TODO Auto-generated method stub
		return repoDao.findByRepo(r_username);
	}

	@Override
	public int insert(RepoVO repoVO) {
		// TODO Auto-generated method stub
		return repoDao.insert(repoVO);
	}

	@Override
	public int update(RepoVO repoVO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(String r_username) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void create_repo_table() {
		// TODO Auto-generated method stub
		
	}

}
