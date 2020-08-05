package com.itbank.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class homeComtroller {
	
	@RequestMapping(value="signup/")
	public void signUp() {}
	
	@RequestMapping(value="myinfo/")
	public void myinfo() {}
	
	@RequestMapping(value="makestudy/")
	public void makestudy() {}
}
