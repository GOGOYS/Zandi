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
import com.heavenstar.zandi.service.RepoService;
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
		
		UserVO username = (UserVO)session.getAttribute("USER");
		
		/*
		 * List<RepoVO> repoList = repoService.findByAllRepo(username.username);
		 * if(!(repoList == null)) { model.addAttribute("REPOLIST",repoList); }
		 */
		
		model.addAttribute("USER",username);
		
		List<RepoListVO> getlist =gitService.getRepoList(username.username);
		
		List<String> repoList = new ArrayList<>();
		for(int i =0; i<getlist.size(); i++) {
			
			String reponame = getlist.get(i).name;
			repoList.add(reponame);
		}
		log.debug("아아:{}",repoList);
		model.addAttribute("REPONAME",repoList);
		
		return "git/home";
	}

	
	@RequestMapping(value="/detail_repo/{seq}",method=RequestMethod.GET)
	public String detail_repo(@PathVariable("seq") String seq, Model model,HttpSession session )throws IOException, ParseException {
		
		UserVO username = (UserVO)session.getAttribute("USER");
		int intSeq = Integer.valueOf(seq);
		
		List<RepoListVO> repoList = gitService.getRepoList(username.username);
		
		for(int i=1; i< repoList.size(); i++) {
			if(i == intSeq) {
				String reponame = repoList.get(i).name;
				int gitOk = gitService.CommitOk(username.username, reponame);
				if(gitOk > 0) {
					model.addAttribute("TODAYOK","OK");
				}else {
					model.addAttribute("TODAYOK","NO");
					
				}
				List<GitCommitVO> gitList = gitService.allCommit(username.username, reponame);
				model.addAttribute("GITLIST",gitList);
				
			}
		}
		
	
		
		
		return "git/detail_repo";
	}
	
	
}
