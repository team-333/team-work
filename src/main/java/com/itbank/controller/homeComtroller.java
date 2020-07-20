package com.itbank.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class homeComtroller {
	
	@RequestMapping(value="signup/")
	public void signUp() {}
	
	@RequestMapping(value="main/")
	public void main() {}
	
	@RequestMapping(value="myprofile/")
	public void myprofile() {}
	
	@RequestMapping(value="mystudies/")
	public void mystudies() {}
	
	@RequestMapping(value="myinfo/")
	public void myinfo() {}
}
