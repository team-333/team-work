package com.itbank.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.itbank.service.MembersService;
import com.itbank.vo.MembersVO;

@Controller
public class loginController {

	@Autowired MembersService ms;
	
	
	@RequestMapping(value="searchpw")
	public void searchpw() {};
	
	
	@RequestMapping(value="checkemail", method=RequestMethod.GET,produces="application/text;charset=utf8")
	@ResponseBody 	
	public String checkemail(HttpServletRequest request) {
		try {
			String email = request.getParameter("email");
			boolean exist = ms.emailcheck(email);
			System.out.println(exist);
			return exist ? "사용중" : "생성가능";
			
		} catch (Exception e) {	
			return "통신 실패 ";
		}
	}
	
	
	
	@RequestMapping(value="join",method=RequestMethod.POST)
	public ModelAndView join(MembersVO vo) {
		ModelAndView mv = new ModelAndView("joinresult");
		
		MembersVO vo2 = ms.insertMembers(vo);
		
		if(vo2 == null) {
			mv.setViewName("signup");
		}
		return mv;
	}
	
	
	
	@RequestMapping(value="login",method=RequestMethod.POST)
	@ResponseBody
	public String login(HttpSession session, MembersVO vo,HttpServletRequest request) {
		
		MembersVO check = ms.selectMembers(vo);
		System.out.println("email :" +vo.getEmail());
		if(check != null) {
		session.setAttribute("login", check);
		return "login";
		}

		return "fail";
	}
	
}
