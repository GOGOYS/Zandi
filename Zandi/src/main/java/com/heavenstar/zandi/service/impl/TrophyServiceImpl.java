package com.heavenstar.zandi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.heavenstar.zandi.model.CommentVO;
import com.heavenstar.zandi.model.TrophyVO;
import com.heavenstar.zandi.persistance.TrophyDao;
import com.heavenstar.zandi.service.TrophyService;

public class TrophyServiceImpl implements TrophyService{
	
	@Autowired
	private TrophyDao trophyDao;

	@Override
	public List<TrophyVO> selectAll() {
		// TODO Auto-generated method stub
		return trophyDao.selectAll();
	}

	@Override
	public CommentVO findByUserTrophy(long t_seq) {
		// TODO Auto-generated method stub
		return trophyDao.findByUserTrophy(t_seq);
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
