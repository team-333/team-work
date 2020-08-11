package com.itbank.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.itbank.service.DelegateService;
import com.itbank.vo.MemberTeamVO;
import com.itbank.vo.StudyVO;




@Controller
public class DelegateController {

	@Autowired private DelegateService ds;
	
	@RequestMapping(value = "delegate/{teamId}/")
	public ModelAndView delegate(HttpServletRequest request,@PathVariable int teamId) {
		
		ModelAndView mav = new ModelAndView("delegate");
		
		List<MemberTeamVO> waitMember = ds.waitMember(teamId); 
		
		
		
		if(waitMember.size() != 0) {
			mav.addObject("wait", ds.waiting(waitMember)); // 신청 대기중인 팀원정보
		}
		
		
		
		mav.addObject("team", ds.team(teamId)); // 팀 정보
		
		List<MemberTeamVO> mtv = ds.memberTeam(teamId);
		mav.addObject("member", ds.MemberTeamList(mtv)); //속한 팀원정보
		
		
		
		return mav;
	}
	
	
	
	@RequestMapping(value = "MemberTeam/{memberId}/{teamId}/{chk}/")
	public ModelAndView memberTeam(@PathVariable int memberId,@PathVariable int teamId, @PathVariable int chk) {
		ModelAndView mav = new ModelAndView("redirect");
		
		System.out.println("Chk" + chk);
		MemberTeamVO mt = new MemberTeamVO();
		System.out.println("memberId" + memberId);
		System.out.println("teamId" + teamId);
		
		
		mt.setMemberId(memberId);
		mt.setTeamId(teamId);
		
		
		if(chk == 1) {

		int joinChk = ds.joinMember(mt);
		
		System.out.println(joinChk);
		
			if(joinChk != 0) {
				mav.addObject("msg", "승인 완료");
			}
		
		}
		
		ds.deleteWating(mt);
		mav.addObject("url", "delegate/" + teamId + "/");
		
		return mav;
	}
	
	@RequestMapping(value = "studydelete/{teamId}/")
	public ModelAndView studydelete(@PathVariable int teamId) {
		ModelAndView mav = new ModelAndView("redirect");
		
		StudyVO deleteChk =ds.studydeleteChk(teamId);
		
	
		System.out.println("deleteChk.getDeleteTime() : " + deleteChk.getDeleteTime());
		if(deleteChk.getDeleteTime().equals("null")) {
			
		mav.addObject("msg", "삭제예정");
		mav.addObject("url", "delegate/" + teamId + "/");
		int result = ds.studyDelete(teamId);
		System.out.println(result);
		}
		
		else {
			mav.addObject("msg", "삭제중");
			mav.addObject("url", "delegate/" + teamId + "/");
		
		}
		
		
		
		return mav;
	}
}


