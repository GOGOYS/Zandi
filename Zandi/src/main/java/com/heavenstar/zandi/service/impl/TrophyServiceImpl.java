package com.heavenstar.zandi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.heavenstar.zandi.model.TrophyVO;
import com.heavenstar.zandi.persistance.TrophyDao;
import com.heavenstar.zandi.service.TrophyService;

@Service
public class TrophyServiceImpl implements TrophyService{
	
	@Autowired
	private TrophyDao trophyDao;

	@Override
	public List<TrophyVO> selectAll() {
		// TODO Auto-generated method stub
		return trophyDao.selectAll();
	}

	@Override
	public List<TrophyVO> findByUserTrophy(String username) {
		// TODO Auto-generated method stub
		return trophyDao.findByUserTrophy(username);
	}

	@Override
	public int insert(TrophyVO trophyVO) {
		// TODO Auto-generated method stub
		return trophyDao.insert(trophyVO);
	}

	@Override
	public int delete(long t_seq) {
		// TODO Auto-generated method stub
		return 0;
	}

}
