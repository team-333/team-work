package com.itbank.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.itbank.service.MembersService;
import com.itbank.vo.MembersVO;

@Controller
public class InfoController {
	
	@Autowired MembersService ms; 
	
	@RequestMapping(value="myprofile/{email}")
	public ModelAndView myprofile(@PathVariable String email,HttpSession session,HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("myprofile");
		try {
			MembersVO vo = (MembersVO) session.getAttribute("login");
			System.out.println("myprofile접속");
			System.out.println(vo.getEmail());
		
		vo =ms.selectMember(vo.getEmail());
		System.out.println(vo.getIntroduceContext());
		mv.addObject("login",vo);
		return mv;
		}catch(Exception e ) {
			System.out.println("오류");
		}
		mv.setViewName("redirect:/");
		return mv;
	}
	
	
	@RequestMapping(value="updatetitle/",produces ="application/text; charset=utf8")
	@ResponseBody 	
	public String updateTitle(HttpServletRequest request,HttpSession session) {
		try {
		
		MembersVO vo = (MembersVO) session.getAttribute("login");
		vo.setIntroduce(request.getParameter("text"));
		ms.updateTitle(vo);	

		return vo.getIntroduce();
		
		}catch(Exception e) {
			return "통신실패";
		}
	}
	
	@RequestMapping(value="updatecontext/",produces ="application/text; charset=utf8")
	@ResponseBody 	
	public String updateContext(HttpServletRequest request,HttpSession session) {
		try {
		
		MembersVO vo = (MembersVO) session.getAttribute("login");
		vo.setIntroduceContext(request.getParameter("text"));
		ms.updateContext(vo);	

		return vo.getIntroduceContext();
		
		}catch(Exception e) {
			return "통신실패";
		}
	}
	
	
	@RequestMapping(value="updateusername/",produces="application/text; charset=utf8")
	@ResponseBody
	public String updateUsername(HttpServletRequest request,HttpSession session) {
		
		try {
			MembersVO vo = (MembersVO) session.getAttribute("login");
			vo.setUsername(request.getParameter("text"));
			ms.updateUsername(vo);
			
			return vo.getUsername();
		}catch(Exception e) {
			return "통신실패";
		}
	}
	
	@RequestMapping(value="updateemail/",produces="application/text; charset=utf8")
	@ResponseBody
	public String updateEmail(HttpServletRequest request,HttpSession session) {
		
		try {
			MembersVO vo = (MembersVO) session.getAttribute("login");
			vo.setEmail(request.getParameter("text"));
			ms.updateEmail(vo);
			
			return vo.getEmail();
		}catch(Exception e) {
			return "통신실패";
		}
	}
	
	
	@RequestMapping(value="checkpassword/",produces="application/text;charset=utf8")
	@ResponseBody
	public String checkpassword(HttpSession session,HttpServletRequest request) {
		
		try {
			System.out.println("checkpassword 접속");
			MembersVO vo = (MembersVO) session.getAttribute("login");
			vo.setPassword(request.getParameter("password"));
			MembersVO check = ms.selectMembers(vo);
			if(check==null) {
				return "실패";
			}
			else return  "성공";

		}catch(Exception e) {
			return "통신실패";
		}
	}
	
	@RequestMapping(value="updatepassword/",method=RequestMethod.POST,produces="application/text;charset=utf8")
	@ResponseBody
	public String updatepassword(HttpSession session,@RequestBody Map<String, Object> params) {
		
		try {
			String newpassword=(String)params.get("newpassword");
			MembersVO vo =(MembersVO) session.getAttribute("login");
			vo.setPassword(newpassword);
			ms.updatePassword(vo);
			System.out.println("업데이트완료");
			return "ok";
		}catch(Exception e) {
			return "통신실패";
		}
		
	}
}
