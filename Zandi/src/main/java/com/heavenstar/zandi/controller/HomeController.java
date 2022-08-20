package com.heavenstar.zandi.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.heavenstar.zandi.model.UserVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HomeController {
	
	

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(HttpSession session, Model model) {
		
		UserVO userVO = (UserVO)session.getAttribute("USER");
		
		if(userVO == null) {
			model.addAttribute("LAYOUT","NOT_LOGIN");
			
		}
		
		return "home";
	}
	
}
