package com.itbank.controller;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.itbank.service.StudyService;
import com.itbank.vo.MembersVO;

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
	
	@RequestMapping(value="makestudy/", method = RequestMethod.POST )
	public ModelAndView makestudy (MultipartHttpServletRequest mpRequest,HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("redirect:/main/");
		
		HttpSession session = request.getSession();
	    MembersVO vo = (MembersVO) session.getAttribute("ls");
		
		int result = ss.insertStudy(mpRequest,vo.getMemberId());
		System.out.println(result);
		
		return mav;
	}
	
}
