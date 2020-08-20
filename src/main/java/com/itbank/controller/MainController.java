package com.itbank.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

	@RequestMapping(value = "main/", method = RequestMethod.GET)
	public ModelAndView main(HttpSession session) {
		ModelAndView mav = new ModelAndView("main");

		mav.addObject("studylist", ss.selectAllStudies());
		
		if (session.getAttribute("login") != null) {
			MembersVO vo = (MembersVO) session.getAttribute("login");
			mav.addObject("memberStudylist", ss.selectMemberStudies(vo.getMemberId()));

		}

		return mav;
	}

	@GetMapping("search/")
	public ModelAndView headersearch(@RequestParam("query") String query) {
		ModelAndView mv = new ModelAndView("search");
		
		if (query.equals("") || query == null) {
			mv.addObject("searchedByName", null);
		}
		else {
			List<StudyVO> searchedByName = ss.searchedByName(query);
			mv.addObject("searchedByName", searchedByName);
		}
		List<StudyVO> searchedByTag = ss.searchedByTag(query);
		
		mv.addObject("searchedByTag", searchedByTag);
		
		mv.addObject("query", query);
		
		return mv;
	}

	@RequestMapping(value = "makestudy/", method = RequestMethod.POST)
	public ModelAndView makestudy(MultipartHttpServletRequest mpRequest, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("redirect:/main/");

		HttpSession session = request.getSession();
		MembersVO vo = (MembersVO) session.getAttribute("login");
		
		
		int result = ss.insertStudy(mpRequest, vo.getMemberId());

		return mav;
	}

	@RequestMapping(value = "study/{teamId}/", method = RequestMethod.GET)
	public ModelAndView studymain(@PathVariable int teamId, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("study");

		StudyVO team = ss.selectStudy(teamId);

		List<MemberTeamVO> memberTeamChk = ds.memberTeam(teamId);
		HttpSession session = request.getSession();
		MembersVO vo = (MembersVO) session.getAttribute("login");

		for(int i =0; i< memberTeamChk.size(); i++) {
			if(memberTeamChk.get(i).getMemberId() == vo.getMemberId()) {
				mav.addObject("memberChk", 1);
			}
		}

		mav.addObject("teamInfo", team);
		mav.addObject("captain", ms.selectMember(team.getDelegate()));

		return mav;
	}

	@RequestMapping(value = "joininstudy/{teamId}/", method = RequestMethod.GET)
	public ModelAndView studyjoin(@PathVariable int teamId, HttpSession session) {
		// redirect 페이지 === alter 페이지
		ModelAndView mav = new ModelAndView("redirect");

		StudyVO sv = ss.selectStudy(teamId);
		MembersVO vo = (MembersVO) session.getAttribute("login");

		MemberTeamVO mtv = new MemberTeamVO();
		mtv.setMemberId(vo.getMemberId());
		mtv.setTeamId(teamId);

		MemberTeamVO searchMemberTeam = ds.searchMemberTeam(mtv);
		MemberTeamVO searchWait = ds.searchWait(mtv);

		if (sv.getTeamPublic() == 0) {

			if (searchMemberTeam == null) { // 이미 가입되어있는지 체크
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

			if (searchWait == null) { // 신청 대기중인지 중복체크
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
