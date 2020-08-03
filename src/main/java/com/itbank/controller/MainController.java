package com.itbank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.itbank.service.StudyService;

@Controller
public class MainController {
	
	@Autowired
	private StudyService ss;
	
	
	@RequestMapping(value="main/", method = RequestMethod.GET )
	public ModelAndView main () {
		ModelAndView mav = new ModelAndView("main");
		
		mav.addObject("studylist", ss.selectAllStudies());
		
		return mav;
	}
}
