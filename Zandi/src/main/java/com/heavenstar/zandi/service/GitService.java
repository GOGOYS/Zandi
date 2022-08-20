package com.heavenstar.zandi.service;

import java.io.IOException;
import java.util.List;

import org.json.simple.parser.ParseException;

import com.heavenstar.zandi.model.GitCommitVO;
import com.heavenstar.zandi.model.ReadmeVO;

public interface GitService {
	
	//깃 하나 가져올때
	public GitCommitVO oneCommit(String id, String repo) throws IOException, ParseException;
	public ReadmeVO getReadme(String id, String repo) throws IOException, ParseException;
	
	// 모든 깃 가져올때
	public List<GitCommitVO> allCommit(String id, String repo) throws IOException, ParseException;
	
	// 커밋 날짜 변환
	public String dataTransate(String date);
	
	//리드미 변환
	public String readmeTransate(String readme);
	
}
