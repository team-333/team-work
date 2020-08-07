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
		mav.addObject("member", ds.MemberTeamList(teamId)); //속한 팀원정보
		
		
		
		return mav;
	}
	
	
	
	@RequestMapping(value = "MemberTeam/{memberId}/{teamId}/{chk}/")
	public ModelAndView memberTeam(@PathVariable int memberId,@PathVariable int teamId, @PathVariable int chk) {
		ModelAndView mav = new ModelAndView("redirect");
		
		System.out.println(chk);
		MemberTeamVO mt = new MemberTeamVO();
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
	
	
}


