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
import com.heavenstar.zandi.model.GroupVO;
import com.heavenstar.zandi.model.RepoListVO;
import com.heavenstar.zandi.model.ToOkVO;
import com.heavenstar.zandi.model.UserVO;
import com.heavenstar.zandi.service.GitService;
import com.heavenstar.zandi.service.GroupService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value="/group")
public class GroupController {
	
	@Autowired
	private GroupService groupService;
	
	@Autowired
	private GitService gitService;
	
	@RequestMapping(value={"/",""},method=RequestMethod.GET)
	public String group(Model model) {
		
		List<GroupVO> groupList = groupService.selectAll();
		
		model.addAttribute("GROUPLIST", groupList);
		
		return "group/home";
	}
	@RequestMapping(value={"/",""},method=RequestMethod.POST)
	public String group(GroupVO groupVO) {
		
		groupVO.setG_inpeople(0);
		groupService.insert(groupVO);
		List<GroupVO> groupList = groupService.selectAll();
		int g_seq = groupList.size();
		
		return "redirect:/group/group_in/"+ g_seq;
	}
	@RequestMapping(value={"/list"},method=RequestMethod.GET)
	public String grouplist() {
		return "redirect:/group";
	}
	
	
	@RequestMapping(value="/group_in/{g_seq}",method=RequestMethod.GET)
	public String group_in(@PathVariable("g_seq") String g_seq, HttpSession session, Model model ) throws IOException, ParseException {
		
		UserVO user = (UserVO)session.getAttribute("USER");
		String userName = user.u_username;
		long longSeq = Long.valueOf(g_seq);
		GroupVO groupName = groupService.findByGroup(longSeq);
		GroupVO group = new GroupVO();
		group.setJ_gname(groupName.getG_name());
		group.setJ_username(userName);
		
		List<GroupVO> peopleList = groupService.findByGroupPeople(groupName.getG_name());
		model.addAttribute("PEOPLELIST",peopleList);
		
		//오늘 커밋 완료 처리
		List<ToOkVO> okList = new ArrayList<>();
		int gitOk = 0;
		for(int i =0; i < peopleList.size(); i++) {
			ToOkVO toOK = new ToOkVO();
			String username = peopleList.get(i).getJ_username();
			toOK.setUsername(username);
			List<RepoListVO> repoList = gitService.getRepoList(username);
			
			
			for(int j =0; j< repoList.size(); j++) {
				String repoName = repoList.get(j).getName();
				gitOk += gitService.CommitOk(username, repoName);
				
			}	
			if(gitOk > 0) {
				toOK.setMessage("완료");
			}else {
				toOK.setMessage("미완료");
			}
			okList.add(toOK);
		}
		
		
		
		
		//입장 처리
		for(int i =0; i < peopleList.size(); i++) {	
			if(peopleList.get(i).getJ_username().equals(userName)) {
				model.addAttribute("GROUP",groupName);
				model.addAttribute("TOOK",okList);
				return "/group/group_in";
			}			
		}
		if(peopleList.size() >= groupName.getG_people()) {
			model.addAttribute("MESSAGE",g_seq);
			return "redirect:/group";
		}
		
		

		//가입 인원이 들어오면 g_inpeople에 1씩 증가하여
		//인원수 카운트 늘리기
		int count = groupName.getG_inpeople();
			count += 1;
			groupName.setG_inpeople(count);
			groupService.updateCount(groupName);

		groupService.insertPeople(group);
		model.addAttribute("GROUP",groupName);
		model.addAttribute("TOOK",okList);
		
		return "redirect:/group/group_in";
		
	}
	

	
	@RequestMapping(value="/out/{g_seq}",method=RequestMethod.GET)
	public String groupOut(@PathVariable("g_seq") String g_seq, HttpSession session) {
		
		UserVO user = (UserVO)session.getAttribute("USER");
		String userName = user.u_username;
		long longSeq = Long.valueOf(g_seq);
		
		GroupVO groupVO = groupService.findByGroup(longSeq);
		String groupName = groupVO.getG_name();
		GroupVO people = groupService.findByOnePeople(groupName, userName);
		groupService.deletePeople(people.getJ_seq());
		
		
		//가입 인원이 들어오면 g_inpeople에 1씩 감소하여
		//인원수 카운트 줄이기
		int count = groupVO.getG_inpeople();
		count -= 1;
		groupVO.setG_inpeople(count);
		groupService.updateCount(groupVO);
		
		// 인원이 0이 되면 그룹 삭제
		if(count < 1) {
			groupService.delete(longSeq);
		}
		
		return "redirect:/group";
	}
	

	
}
