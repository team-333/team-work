package com.itbank.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class homeComtroller {
	
	@RequestMapping(value="signup/")
	public void signUp() {}

	@RequestMapping(value="myprofile/")
	public void myprofile() {}
	
	@RequestMapping(value="mystudies/")
	public void mystudies() {}
	
	@RequestMapping(value="myinfo/")
	public void myinfo() {}
	
	@RequestMapping(value="makestudy/")
	public void makestudy() {}
}
