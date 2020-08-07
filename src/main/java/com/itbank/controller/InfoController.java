package com.itbank.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.itbank.service.MembersService;
import com.itbank.service.StudyService;
import com.itbank.vo.MembersVO;

@Controller
public class InfoController {

	@Autowired MembersService ms; 
	@Autowired
	private StudyService ss;
	
	@RequestMapping(value="myinfo/{memberId}/")
	public ModelAndView myinfo(@PathVariable("memberId") String memberId ) {
		ModelAndView mv = new ModelAndView("myinfo");
		return mv;
	}
	
	@GetMapping (value = {"myprofile/", "myprofile/{memberId}/"} )
	public ModelAndView myprofile(@PathVariable("memberId") String memberId ,HttpSession session,HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("myprofile");

		try {
			MembersVO vo = (MembersVO) session.getAttribute("login");
			System.out.println("myprofile접속");
			System.out.println(vo.getEmail());
		
			// 네이버 로그인시 멤버아이디가 없으므로 if 문으로 session값에서 email을 받아와서 그것만 띄워줌
			// 내 정보 관리창에 회원가입으로 가는 버튼 만들기
			vo = ms.selectMember(vo.getMemberId());
			System.out.println(vo.getIntroduceContext());
			mv.addObject("login",vo);
			
			return mv;
		
		} catch(Exception e ) {
			System.out.println("오류");
		}
		
		mv.setViewName("redirect:/");
		
		return mv;
	}
	
	@RequestMapping(value="mystudies/{memberId}/")
	public ModelAndView mystudies (@PathVariable int memberId, HttpSession session) {
		ModelAndView mav = new ModelAndView("mystudies");
		
		mav.addObject("studylist", ss.selectAllStudies());
		if(session.getAttribute("login") != null) {
			MembersVO vo = (MembersVO) session.getAttribute("login");
			mav.addObject("memberStudylist", ss.selectMemberStudies(vo.getMemberId()));
		}
		
		return mav;
	}
	

	@PostMapping(value="updatetitle/",produces ="application/text; charset=utf8")
	@ResponseBody 	
	public String updateTitle(HttpServletRequest request,HttpSession session) {
		try {

		MembersVO vo = (MembersVO) session.getAttribute("login");
		vo.setIntroduce(request.getParameter("text"));
		System.out.println("업데이트 수행");
		ms.updateTitle(vo);	

		return vo.getIntroduce();

		}catch(Exception e) {
			System.out.println(e);
			return "통신실패";
		}
	}
	
	
	@PostMapping(value="updatecontext/",produces ="application/text; charset=utf8")
	@ResponseBody 	
	public String updateContext(HttpServletRequest request,HttpSession session) {
		try {
			
			// textarea의 \n표시를 <br>로 replace
		MembersVO vo = (MembersVO) session.getAttribute("login");
		vo.setIntroduceContext(request.getParameter("text"));
		ms.updateContext(vo);
		
		return vo.getIntroduceContext();

		}catch(Exception e) {
			System.out.println("updateContext 에러 : " + e);
			return "통신실패";
		}
	}


	@PostMapping(value="updateusername/",produces="application/text; charset=utf8")
	@ResponseBody
	public String updateUsername(HttpServletRequest request,HttpSession session) {

		try {
			// 이름 업데이트 시 왼쪽 콘솔의 이름도 변경
			MembersVO vo = (MembersVO) session.getAttribute("login");
			vo.setUsername(request.getParameter("text"));
			ms.updateUsername(vo);

			return vo.getUsername();
		}catch(Exception e) {
			return "통신실패";
		}
	}

	@PostMapping(value="updateemail/",produces="application/text; charset=utf8")
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


	@PostMapping(value="checkpassword/",produces="application/text;charset=utf8")
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

	@PostMapping(value="updatepassword/",produces="application/text;charset=utf8")
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