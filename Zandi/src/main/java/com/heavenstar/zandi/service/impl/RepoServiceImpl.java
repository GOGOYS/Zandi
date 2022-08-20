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
	public List<RepoVO> findByAllRepo(String r_username) {
		// TODO Auto-generated method stub
		return repoDao.findByAllRepo(r_username);
	}
	
	@Override
	public RepoVO findByOneRepo(long r_seq) {
		// TODO Auto-generated method stub
		return repoDao.findByOneRepo(r_seq);
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
