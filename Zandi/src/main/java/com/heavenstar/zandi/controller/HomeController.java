package com.heavenstar.zandi.controller;

import java.text.ParseException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.heavenstar.zandi.model.UserVO;
import com.heavenstar.zandi.service.GroupService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HomeController {
	
	@Autowired
	private GroupService groupService;
	
	

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(HttpSession session, Model model) throws ParseException {
		
		UserVO userVO = (UserVO)session.getAttribute("USER");
		
		if(userVO == null) {
			model.addAttribute("LAYOUT","NOT_LOGIN");
			
		}
		
		groupService.periodCheck("2022-10-1","2022-11-27");
		
		return "home";
	}
	
}
