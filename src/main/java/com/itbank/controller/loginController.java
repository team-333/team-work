package com.itbank.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.itbank.mail.MailUtil;
import com.itbank.service.MembersService;
import com.itbank.vo.MembersVO;

@Controller
public class loginController {

	@Autowired MembersService ms;

	@RequestMapping(value="searchpw/",method=RequestMethod.POST)
	public ModelAndView searchpw(MembersVO vo) throws Exception{
		ModelAndView mv = new ModelAndView("resultpage");
		System.out.println(ms.emailcheck(vo.getEmail()));
		if(ms.emailcheck(vo.getEmail())) {
			
			
			MailUtil mu= new MailUtil();
			String newp=MailUtil.createKey();
			vo.setPassword(newp);
			ms.updatepw(vo);
			
			String subject = "열공팀에서 보낸 임시비밀번호";
			String msg="";
			msg+="<div align='center' style='border:1px solid black; font-family : verdana'>";
			
			msg+="<h3 style ='color : blue;'>임시비밀번호입니다</h3>";
			msg+="<h2><strong>";
			msg+=newp;
			msg+="</strong></h2>";
			msg+="</div>";
			mu.sendMail(vo.getEmail(), subject, msg);
			mv.addObject("msg","이메일 발송 완료");
			mv.addObject("url","");
			return mv;
		}
		mv.addObject("msg","일치하는 이메일이 없습니다.");
		mv.addObject("url","searchpw/");
		return mv;
	}
	
	
	@RequestMapping(value="searchpw/",method=RequestMethod.GET)
	public void searchpw() {};
	
	
	@RequestMapping(value="checkemail/", method=RequestMethod.GET,produces="application/text;charset=utf8")
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
	
	
	
	@RequestMapping(value="join/",method=RequestMethod.POST)
	public ModelAndView join(MembersVO vo) {
		ModelAndView mv = new ModelAndView("joinresult");
		
		MembersVO vo2 = ms.insertMembers(vo);
		
		if(vo2 == null) {
			mv.setViewName("signup");
		}
		return mv;
	}
	
	
	
	@RequestMapping(value="login/",method=RequestMethod.POST)
	public ModelAndView login(HttpSession session, MembersVO vo) {
		ModelAndView mv = new ModelAndView("redirect:/main/");
		MembersVO check = ms.selectMembers(vo);
		System.out.println("email :" +vo.getEmail());
		if(check != null) {
			session.setAttribute("ls", check);
			System.out.println("로그인성공");
			return mv;
		}
		mv.setViewName("redirect:/");
		return mv;
	}
	
	@RequestMapping(value="logout/")
	public ModelAndView logout(HttpSession session) {
		ModelAndView mv = new ModelAndView("redirect:/");
		System.out.println("로그아웃성공");
		session.invalidate();
		return mv;
	}
	
	@RequestMapping("policy/")
	public String policy(HttpServletRequest req) {
		try {
			System.out.println(req.getSession().getServletContext().getRealPath("") + "resources\\policy\\policy.txt");
			String ServletPath = req.getSession().getServletContext().getRealPath("") + "resources\\policy\\policy.txt";
			File policy = new File(ServletPath);
			Scanner scan = new Scanner(policy);
			String lines = "";
			while(scan.hasNextLine()) {
				lines += scan.nextLine() + "<br>";
			}
			
			req.setAttribute("text", lines);
			
			scan.close();
		
		}catch(FileNotFoundException e) {
			System.out.println("FileNotFoundException - Policy" );
		}
		
		return "policy";
	}
	
	
}
