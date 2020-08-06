package com.itbank.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
	public ModelAndView main (HttpSession session) {
		ModelAndView mav = new ModelAndView("main");
		
		mav.addObject("studylist", ss.selectAllStudies());
		if(session.getAttribute("login") != null) {
			MembersVO vo = (MembersVO) session.getAttribute("login");
			mav.addObject("memberStudylist", ss.selectMemberStudies(vo.getMemberId()));
		}
		
		return mav;
	}
	
	@RequestMapping(value="makestudy/", method = RequestMethod.POST )
	public ModelAndView makestudy (MultipartHttpServletRequest mpRequest, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("redirect:/main/");
		
		HttpSession session = request.getSession();
		MembersVO vo = (MembersVO) session.getAttribute("login");
		
		// 이미지 바이너리 데이터로 저장하기
		int result = ss.insertStudy(mpRequest, vo.getMemberId());
		System.out.println("스터디 만들기 이미지 저장 완료 : " + result);

		return mav;
	}
	
	@RequestMapping(value="study/{teamId}/", method = RequestMethod.GET )
	public ModelAndView studymain (@PathVariable int teamId) {
		ModelAndView mav = new ModelAndView("study");
		
		mav.addObject("teamInfo", ss.selectStudy(teamId));
		
		return mav;
	}
	
}
