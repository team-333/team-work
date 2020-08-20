package com.itbank.controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.itbank.service.DelegateService;
import com.itbank.service.MessageService;
import com.itbank.service.StudyService;
import com.itbank.vo.MemberTeamVO;
import com.itbank.vo.MessageVO;
import com.itbank.vo.StudyVO;




@Controller
public class DelegateController {

	@Autowired private DelegateService ds;
	
	@Autowired private StudyService ss;
	
	@Autowired private MessageService ms;

	@RequestMapping(value = "delegate/{teamId}/")
	public ModelAndView delegate(HttpServletRequest request,@PathVariable int teamId) {

		ModelAndView mav = new ModelAndView("delegate");

		List<MemberTeamVO> waitMember = ds.waitMember(teamId); 

		if(waitMember.size() != 0) {
			mav.addObject("wait", ds.waiting(waitMember)); // 신청 대기중인 팀원정보
		}
		
		
		List<MessageVO> message =  ms.teamSearchMessage(teamId);
		List<MessageVO> messageResult =  new ArrayList<MessageVO>();
		System.out.println(message);

		for(int i = 0; i<message.size(); i++) {
			System.out.println("메시지 체크중");
			MessageVO vo = message.get(i);	
			String timeChange = vo.getTime();
			String timeArr[] = timeChange.split(" ");

			String mmss = timeArr[3];
			String mmssArr[] = mmss.split(":");

			int mm = Integer.parseInt(mmssArr[0]);

			if(mm > 12) {
				mmss = "오후 " +mm+":"+mmssArr[1]; 
			}
			else {
				mmss = "오전 " +mm+":"+mmssArr[1]; 
			}


			System.out.println(mmss);
			vo.setTime(mmss);

			messageResult.add(i, vo);
		}

		mav.addObject("message", messageResult );

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
		
		if(chk == 3) {
			int deleteResult = ds.deleteMember(mt);
			if(deleteResult != 0) {
				mav.addObject("msg", "추방 완료");
			}
		}


		ds.deleteWating(mt);
		mav.addObject("url", "delegate/" + teamId + "/");

		return mav;
	}

	
	@RequestMapping(value="signout/{teamId}/{memberId}/")
	   public ModelAndView singout(@PathVariable int memberId,@PathVariable int teamId) {
	       ModelAndView mv = new ModelAndView("redirect");
	      MemberTeamVO mt = new MemberTeamVO();
	      
	      int delegate = ss.selectStudy(teamId).getDelegate();
	   
	      if(delegate == memberId) {
	    	 mv.addObject("url","mystudies/" + memberId + "/");
	         mv.addObject("msg", "조장은 탈퇴가 안됩니다.");
	      }
	      
	      else {
	      mt.setMemberId(memberId);
	      mt.setTeamId(teamId);
	      ds.signout(mt);
	      mv.addObject("url","mystudies/" + memberId + "/");
	      mv.addObject("msg", "탈퇴완료");
	      }
	      return mv;
	   }

	@RequestMapping(value = "studydelete/{teamId}/")
	public ModelAndView studydelete(@PathVariable int teamId) {
		ModelAndView mav = new ModelAndView("redirect");

		StudyVO deleteChk =ds.studydeleteChk(teamId);


		System.out.println("deleteChk.getDeleteTime() : " + deleteChk.getDeleteTime());
		if(deleteChk.getDeleteTime() == null) {
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