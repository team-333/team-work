package com.itbank.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.itbank.service.DelegateService;
import com.itbank.service.MembersService;
import com.itbank.service.StudyService;
import com.itbank.vo.MemberTeamVO;
import com.itbank.vo.MembersVO;
import com.itbank.vo.StudyVO;

@Controller
public class MainController {
	
	@Autowired
	private StudyService ss;
	
	@Autowired
	private MembersService ms;
	
	@Autowired
	private DelegateService ds;
	
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
	
	
	@GetMapping(value="searchstudy/{text}/",produces="application/text;charset=utf8")
	@ResponseBody
	public ModelAndView searchstudy(@PathVariable String text,HttpSession session,HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("studylist");
		MembersVO vo = (MembersVO) session.getAttribute("login");
		List<StudyVO> list = ss.selectMemberStudies(vo.getMemberId());
		
		if(text.equals("check")) {
			mv.addObject("memberStudylist", list);
		}
		else {
			List<Integer> mt= ss.selectTeamId(vo.getMemberId());
			for(int k =0;k<mt.size();k++) {
				System.out.println("memberteam 값 :" +mt.get(k));
			}
			List<StudyVO> reallist = new ArrayList<StudyVO>();
			list = ss.searchStudylist(text)	;
			System.out.println("list사이즈 : "+list.size());
			System.out.println("mst사이즈 : "+mt.size());
			for(int i=0;i<list.size();i++) {
				
				for(int j=0;j<mt.size();j++) {
					
					String chk = list.get(i).getTeamId() + "";
					String chk2 = mt.get(j) + "";
					if( chk.equals(chk2)) {
					System.out.println(chk + "//"+mt.get(j)+" : 팀번호"+j + " :검색결과" + i);
						reallist.add(list.get(i));
						System.out.println(reallist.get(i).getTeamName());
					
					}
			}
			
			mv.addObject("memberStudylist", reallist);
		}
		}	
		return mv;
	}
	
	@RequestMapping(value="makestudy/", method = RequestMethod.POST )
	public ModelAndView makestudy (MultipartHttpServletRequest mpRequest, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("redirect:/main/");
		
		HttpSession session = request.getSession();
		MembersVO vo = (MembersVO) session.getAttribute("login");
		
		int result = ss.insertStudy(mpRequest, vo.getMemberId());

		return mav;
	}
	
	@RequestMapping(value="study/{teamId}/", method = RequestMethod.GET )
	public ModelAndView studymain (@PathVariable int teamId) {
		ModelAndView mav = new ModelAndView("study");
		
		StudyVO team = ss.selectStudy(teamId);
		
		mav.addObject("teamInfo", team);
		mav.addObject("captain", ms.selectMember(team.getDelegate()));
		
		return mav;
	}
	
	@RequestMapping(value="joininstudy/{teamId}/", method = RequestMethod.GET )
	public ModelAndView studyjoin (@PathVariable int teamId, HttpSession session) {
		// redirect 페이지 === alter 페이지
		ModelAndView mav = new ModelAndView("redirect");
		
		StudyVO sv = ss.selectStudy(teamId);
		MembersVO vo = (MembersVO) session.getAttribute("login");
		
		MemberTeamVO mtv = new MemberTeamVO();
		mtv.setMemberId(vo.getMemberId());
		mtv.setTeamId(teamId);
		
		MemberTeamVO searchMemberTeam = ds.searchMemberTeam(mtv);
		MemberTeamVO searchWait = ds.searchWait(mtv);
		
		
		
		if(sv.getTeamPublic() == 0) {
			
			if(searchMemberTeam == null) {		// 이미 가입되어있는지 체크
				int result = ss.joinStudy(teamId, vo.getMemberId());
			
				if (result != 1) {
					mav.addObject("msg", "가입실패");
					mav.addObject("url", "study/" + teamId + "/");
				}
			
				mav.addObject("msg", "가입완료");
				mav.addObject("url", "study/" + teamId + "/");
			}
			
			else {
				mav.addObject("msg", "이미 가입된 멤버");
				mav.addObject("url", "study/" + teamId + "/");
			}
			
			
		}
		
		else {
			
			
			if(searchWait == null) { 		// 신청 대기중인지 중복체크
				ss.waitingTeam(teamId, vo.getMemberId());
				mav.addObject("msg", "가입 신청 완료");
				mav.addObject("url", "study/" + teamId + "/");
			}
			
			else {
				mav.addObject("msg", "이미 가입신청된 멤버");
				mav.addObject("url", "study/" + teamId + "/");
			}

		}
	
		
		return mav;
	}
}
