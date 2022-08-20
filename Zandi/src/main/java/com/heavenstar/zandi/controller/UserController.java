package com.heavenstar.zandi.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.heavenstar.zandi.model.UserVO;
import com.heavenstar.zandi.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value="/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/join",method=RequestMethod.GET)
	public String join() {
		
		return null;
	}
	
	@RequestMapping(value="/join",method=RequestMethod.POST)
	public String join(UserVO userVO, Model model) {
		
		UserVO joinVO = userService.findById(userVO.username);
		
		if(joinVO == null) {
			
			log.debug("아아아아:{}",userVO);
			userService.insert(userVO);
			
			return "redirect:/user/login";
		}
		
		return "user/join";		
	}
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login() {
		
		return "user/login";
	}
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(UserVO userVO,Model model, HttpSession session) {
		
		UserVO user = userService.findById(userVO.username);
		
		if(user.password.equals(userVO.password)) {
			session.setAttribute("USER", userVO);
			return "redirect:/";
		}
		model.addAttribute("error","LOGIN_FAIL");
		return "user/login";
	}

	
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public String logout(HttpSession session) {
		
		session.setAttribute("USER", null);
		
		return "redirect:/";
	}
}
