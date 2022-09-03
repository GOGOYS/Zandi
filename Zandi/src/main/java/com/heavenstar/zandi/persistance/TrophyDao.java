package com.heavenstar.zandi.persistance;

import java.util.List;

import com.heavenstar.zandi.model.CommentVO;
import com.heavenstar.zandi.model.TrophyVO;

public interface TrophyDao {
	
	public List<TrophyVO> selectAll();
	public CommentVO findByUserTrophy(long t_seq);
	public int insert(TrophyVO trophyVO);
	public int delete(long t_seq);

}
