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
import com.heavenstar.zandi.model.GroupVO;
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
	public String group(HttpSession session  ,Model model) {
		
		
		
		List<GroupVO> groupList = groupService.selectAll();
		
		
		
		model.addAttribute("GROUPLIST", groupList);
		
		return "group/home";
	}
	@RequestMapping(value={"/",""},method=RequestMethod.POST)
	public String group(GroupVO groupVO) {
		
		groupService.insert(groupVO);
		
		return "redirect:/group/home";
	}
	
	@RequestMapping(value="/join/{g_seq}",method=RequestMethod.GET)
	public String join(@PathVariable("g_seq") String g_seq, HttpSession session, Model model ) {
		
		UserVO userVO = (UserVO)session.getAttribute("USER");
		GroupVO group = new GroupVO();
		int intSeq = Integer.valueOf(g_seq);
		GroupVO groupName = groupService.findByGroup(intSeq);
		group.setJ_gname(groupName.getG_name());
		group.setJ_username(userVO.username);
		
		List<GroupVO> peopleList = groupService.findByGroupPeople(groupName.getG_name());
		
		int count =0;
		for(int i =0; i < peopleList.size(); i++) {
			
			if(peopleList.get(i).getJ_username() == userVO.username) {
				count = 1;
			}
			
		}
		groupService.insertPeople(group);
		
		
		
		model.addAttribute("GROUP",groupName);
		model.addAttribute("PEOPLELIST",peopleList);
		
		return "/group/group_in";
	}
	
	@RequestMapping(value="/group_in/{g_seq}",method=RequestMethod.GET)
	public String group_in(@PathVariable("g_seq") String g_seq, Model model) throws IOException, ParseException{
		
		int intSeq = Integer.valueOf(g_seq);
		GroupVO groupName = groupService.findByGroup(intSeq);
		
		List<GroupVO> peopleList = groupService.findByGroupPeople(groupName.getG_name());
		
		model.addAttribute("GROUP",groupName);
		model.addAttribute("PEOPLELIST",peopleList);
		
		for(int i =0; i < peopleList.size(); i++) {
			GitCommitVO git = gitService.oneCommit("유저이름", "레포이름");
		}
		return "/group/group_in";
	}
	
}
