package com.itbank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.itbank.service.MyMessageService;

@Controller
public class MyMessageController {

	@Autowired private MyMessageService mms;
	
	@RequestMapping(value="mymessage/{teamId}/")
	public ModelAndView mymessage(@PathVariable int teamId) {
		ModelAndView mav = new ModelAndView("mymessage");
		return mav;
	}
}