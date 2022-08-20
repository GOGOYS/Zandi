package com.heavenstar.zandi.persistance;

import java.util.List;

import com.heavenstar.zandi.model.RepoVO;

public interface RepoDao {
	
	public List<RepoVO> selectAll();
	public List<RepoVO> findByAllRepo(String r_username);
	public RepoVO findByOneRepo(long r_seq);
	public int insert(RepoVO repoVO);
	public int update(RepoVO repoVO);
	public int delete(String r_username);
	
	public void create_repo_table();
}
