package com.heavenstar.zandi.controller;

import java.io.IOException;
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
import com.heavenstar.zandi.model.ReadmeVO;
import com.heavenstar.zandi.model.RepoVO;
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
	
	@Autowired
	private RepoService repoService;
	
	@RequestMapping(value={"/",""},method=RequestMethod.GET)
	public String home(HttpSession session, Model model){
		
		UserVO username = (UserVO)session.getAttribute("USER");
		
		List<RepoVO> repoList = repoService.findByAllRepo(username.username);
		if(!(repoList == null)) {
			model.addAttribute("REPOLIST",repoList);
		}
		
		model.addAttribute("USER",username);
		
		return "git/home";
	}
	
	@RequestMapping(value={"","/"},method=RequestMethod.POST)
	public String home(String repo, HttpSession session) throws IOException, ParseException{
		
		UserVO username = (UserVO)session.getAttribute("USER");
		
		RepoVO repoVO = new RepoVO();
		
		repoVO.setR_username(username.username);
		repoVO.setR_reponame(repo);
		log.debug(repoVO.toString());
		
		
		repoService.insert(repoVO);
		

		return "redirect:/git";
	}
	
	@RequestMapping(value="/detail_repo/{seq}",method=RequestMethod.GET)
	public String detail_repo(@PathVariable("seq") String r_seq, Model model)throws IOException, ParseException {
		
		
		long longSeq = Long.valueOf(r_seq);
		
		RepoVO repoVO = repoService.findByOneRepo(longSeq);
		
		//한개 커밋 가져오기
		GitCommitVO gitVO = gitService.oneCommit(repoVO.getR_username(),repoVO.getR_reponame());
		gitVO.setReponame(repoVO.getR_reponame());
		
		gitService.readmeTransate(null);
		
		int todayOk = gitService.todayOk(gitVO.getCommitter().getDate());
		if(todayOk > 0) {
			model.addAttribute("TODAYOK","OK");
		}else {
			model.addAttribute("TODAYOK","NO");
			
		}
		model.addAttribute("REPO",gitVO);
		
		//리드미 가져오기
		ReadmeVO readmeVO = gitService.getReadme(repoVO.getR_username(),repoVO.getR_reponame());
		model.addAttribute("README",readmeVO);
		
		//여러개 커밋 가져오기
		List<GitCommitVO> gitList = gitService.allCommit(repoVO.getR_username(),repoVO.getR_reponame());
		model.addAttribute("GITLIST",gitList);
		
		//gitService.readmeTransate(readmeVO.getContent());
		
		return "git/detail_repo";
	}
	
	
}
