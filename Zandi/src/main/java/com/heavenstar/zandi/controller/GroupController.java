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
		
		return "redirect:/group";
	}
	
	@RequestMapping(value="/group_in/{g_seq}",method=RequestMethod.GET)
	public String join(@PathVariable("g_seq") String g_seq, HttpSession session, Model model ) {
		
		UserVO userVO = (UserVO)session.getAttribute("USER");
		long longSeq = Long.valueOf(g_seq);
		GroupVO groupName = groupService.findByGroup(longSeq);
		GroupVO group = new GroupVO();
		group.setJ_gname(groupName.getG_name());
		group.setJ_username(userVO.username);
		
		List<GroupVO> peopleList = groupService.findByGroupPeople(groupName.getG_name());
		
		//이미 가입 했을때
		for(int i =0; i < peopleList.size(); i++) {	
			if(peopleList.get(i).getJ_username().equals(userVO.username)) {
				log.debug("????????ㅇㅁㄴㅇㄴㅁㅇ");
				model.addAttribute("GROUP",groupName);
				model.addAttribute("PEOPLELIST",peopleList);
				
				return "/group/group_in";
			}			
		}
		
		// 
		if(peopleList.size() >= groupName.getG_people()) {
			return "redirect:/group";

		}
			
		
		groupService.insertPeople(group);
		
		model.addAttribute("GROUP",groupName);
		model.addAttribute("PEOPLELIST",peopleList);
		
		return "redirect:/group/group_in";
		
	}
	

	
}
