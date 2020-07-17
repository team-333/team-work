package com.itbank.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.itbank.service.UserService;
import com.itbank.vo.UserVO;

@Controller
public class homeComtroller {
	
	@Autowired UserService us;
	
	@RequestMapping("/")
	public String home() {
		return "home";
	}
	
	@RequestMapping("loginresult")
	public String loginresult() {
		return "loginresult";
	}
	
	
	@RequestMapping("signup")
	public String signup() {
		return "signup";
	}
	@RequestMapping("forgetpw")
	public String forgetpw() {
		return"forgetpw";
	}
	
	@RequestMapping(value="join", method=RequestMethod.GET)
	public String join() {
		return "join";
	}
	@RequestMapping(value="join", method=RequestMethod.POST)
	public ModelAndView join(UserVO vo) {
		ModelAndView mv = new ModelAndView("joinResult");
		mv.addObject("vo", us.join(vo));
		return mv;
	}
	
	@RequestMapping(value="emailcheck/", produces="application/text;charset=utf8")
	@ResponseBody 	// 반환하는 값이 그대로 응답으로 전달된다
	public String idcheck(HttpServletRequest request) {
		try {
			String email = request.getParameter("email");
			
			boolean alreadyExist = us.idcheck(email);
			return alreadyExist ? "이미 사용중인 계정입니다" : "사용 가능한 ID입니다";
			
		} catch (Exception e) {	// 작성시에 문제를 확인하기 위한 예외 처리
			return "통신 실패 : " + e.getClass().getSimpleName();
		}
	}

	
	
	
	@RequestMapping(value="login",method=RequestMethod.POST)
	public ModelAndView login(HttpSession session,UserVO vo) {
		ModelAndView mv = new ModelAndView("redirect");
	
		
		UserVO check = us.login(vo);
		if(check ==null) {
		
		mv.addObject("msg","회원정보가 일치하지 않습니다");
		mv.addObject("url","/");

		}
		else {
			mv.addObject("msg","로그인성공");
			session.setAttribute("list", check);
			mv.setViewName("loginresult");
			mv.addObject("url","loginresult");

		}
		return mv;
	}
}
