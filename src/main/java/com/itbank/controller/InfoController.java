package com.itbank.controller;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartRequest;
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
//			System.out.println(vo.getIntroduceContext());
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
		
			MembersVO vo = (MembersVO) session.getAttribute("login");
			String password =request.getParameter("password");
			MessageDigest md;
			try {
				md = MessageDigest.getInstance("SHA-256");
				md.update(password.getBytes());
				password = String.format("%064x", new BigInteger(1,md.digest()));
			    vo.setPassword(password);
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
			
			vo.setPassword(password);
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
		String password=(String)params.get("newpassword");
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");
			md.update(password.getBytes());
			password = String.format("%064x", new BigInteger(1,md.digest()));
		   
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		
		try {
			
			MembersVO vo =(MembersVO) session.getAttribute("login");
			vo.setPassword(password);
			ms.updatePassword(vo);
			
			return "ok";
		}catch(Exception e) {
			return "통신실패";
		}

	}
	
	@PostMapping(value="uploadpic/")
	public ModelAndView uploadProfilePic(MultipartHttpServletRequest mpRequest, HttpSession session) {
		ModelAndView mav = new ModelAndView("redirect");
		mav.addObject("msg", "upload");

		MembersVO vo = (MembersVO) session.getAttribute("login");
		mav.addObject("url", "myinfo/"+ vo.getMemberId() +"/");
		
		int result = ms.changeProfilePic(mpRequest, vo);
		
		if (result != 1 || mpRequest.getFile("profile-pic").isEmpty()) {
			mav.addObject("msg", "fail");
		}
		System.out.println("result : " + result);
		return mav;
	}
	
	@PostMapping(value="deleteaccount/")
	public ModelAndView deleteaccoun(HttpSession session,HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("redirect");
		MembersVO vo = (MembersVO) session.getAttribute("login");
		int delegatecheck = ms.delegate_overlab(vo.getMemberId());
		try {
			if(delegatecheck==1) {
				mv.addObject("msg", "조장입니다");
				mv.addObject("url", "");
				return mv;
			}
			else if(delegatecheck!=1){
				ms.deleteAccount(vo.getMemberId());
			    ms.deleteTeamMember(vo.getMemberId());
				}
			
		else {
		mv.addObject("msg","탈퇴실패");
		mv.addObject("url","");
		}
		}catch(Exception e) {
			System.out.println("회원 탈퇴 오류 : "+e);
		}

		return mv;
	}
	
	
}