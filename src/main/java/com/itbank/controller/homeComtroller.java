package com.itbank.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.itbank.service.MembersService;
import com.itbank.vo.MembersVO;

@Controller
public class homeComtroller {
	
	@Autowired private MembersService membersService;
	
	@RequestMapping(value="signup/")
	public void signUp() {}

	@RequestMapping(value="myprofile/")
	public ModelAndView myprofile(HttpServletRequest request) {
	
		ModelAndView mav = new ModelAndView("myprofile");
		
		HttpSession session = request.getSession();
	    MembersVO vo = (MembersVO) session.getAttribute("ls");
	   
	    System.out.println(vo.getEmail());
	    

	    return mav;
	}
	
	@RequestMapping(value="mystudies/")
	public void mystudies() {}
	
	@RequestMapping(value="myinfo/")
	public void myinfo() {}
	
	@RequestMapping(value="makestudy/")
	public void makestudy() {}
}
