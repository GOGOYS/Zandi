package com.heavenstar.zandi.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.heavenstar.zandi.model.GitCommitVO;
import com.heavenstar.zandi.model.RepoListVO;
import com.heavenstar.zandi.model.UserVO;
import com.heavenstar.zandi.service.GitService;
import com.heavenstar.zandi.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value="/git")
public class GitController {
	
	@Autowired
	private GitService gitService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value={"/",""},method=RequestMethod.GET)
	public String home(HttpSession session, Model model)throws IOException, ParseException{
		
		UserVO user = (UserVO)session.getAttribute("USER");
		UserVO userVO =userService.findByName(user.u_username);
		String gitName = userVO.getU_github_id();
		
		model.addAttribute("USER",userVO);
		
		List<RepoListVO> getlist =gitService.getRepoList(gitName);
		
		List<String> repoList = new ArrayList<>();
		int gitOk =0;
		for(int i =0; i<getlist.size(); i++) {
			
			String repoName = getlist.get(i).name;	
			repoList.add(repoName);
			gitOk += gitService.CommitOk(gitName, repoName);
			
		}
		
		//오늘의 커밋 검사
		if(gitOk > 0) {
			model.addAttribute("TODAYOK","OK");
		}else {
			model.addAttribute("TODAYOK","NO");	
		}
		model.addAttribute("REPONAME",repoList);
		
		return "git/home";
	}

	
	@RequestMapping(value="/detail_repo/{seq}",method=RequestMethod.GET)
	public String detail_repo(@PathVariable("seq") String seq, Model model,HttpSession session )throws IOException, ParseException {
		
		UserVO user = (UserVO)session.getAttribute("USER");
		UserVO userVO =userService.findByName(user.u_username);
		String gitName = userVO.getU_github_id();
		int intSeq = Integer.valueOf(seq);
		intSeq -= 1;
		
		List<RepoListVO> repoList = gitService.getRepoList(gitName);
		
		for(int i=0; i< repoList.size(); i++) {
			if(i == intSeq) {
				String repoName = repoList.get(i).name;
				int gitOk = gitService.CommitOk(gitName, repoName);
				if(gitOk > 0) {
					model.addAttribute("TODAYOK","OK");
				}else {
					model.addAttribute("TODAYOK","NO");
					
				}
				List<GitCommitVO> gitList = gitService.allCommit(gitName, repoName);
				model.addAttribute("GITLIST",gitList);
				
			}
		}
		
	
		
		
		return "git/detail_repo";
	}
	
	
}
