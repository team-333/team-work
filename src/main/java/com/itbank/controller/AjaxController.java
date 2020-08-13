package com.itbank.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itbank.service.DelegateService;
import com.itbank.service.MessageService;
import com.itbank.service.StudyService;
import com.itbank.vo.MemberTeamVO;
import com.itbank.vo.MembersVO;
//만들고있는코드
import com.itbank.vo.MessageVO;
import com.itbank.vo.StudyVO;

@Controller
public class AjaxController {
	@Autowired
	private DelegateService ds;
	@Autowired
	private MessageService ms;
	@Autowired
	private StudyService ss;
	
	
	@ResponseBody
	@RequestMapping(value = "toggle/{cnt}/{teamId}/", method = RequestMethod.GET, produces = "applcation/text;charset=utf8")
	public String cnt(@RequestBody @PathVariable("cnt") int cnt, @PathVariable("teamId") int teamId) {
		String jsonString = null;
		ObjectMapper jsonMapper = new ObjectMapper();
		List<MemberTeamVO> waitChk = null;
		List<MemberTeamVO> memberChk = null;
		List<MembersVO> member = null;

		try {
			if (cnt % 2 == 0) {
				waitChk = ds.waitMember(teamId);
		
				if (waitChk.isEmpty()) {
					jsonString = "NullString";
				} else {
					member = ds.waiting(waitChk);
					jsonString = jsonMapper.writeValueAsString(member);
				}

			}

			else {

				memberChk = ds.memberTeam(teamId);
			
				if (memberChk.isEmpty()) {
					jsonString = "NullString";
				} else {
					member = ds.MemberTeamList(memberChk);
					jsonString = jsonMapper.writeValueAsString(member);
				}
			}
			

	

		} catch (IOException e) {
			System.out.println("JSON 파싱 에러 !!");
		}
		
		return jsonString;
	}

	@ResponseBody
	@RequestMapping(value = "message/", method = RequestMethod.POST, produces = "applcation/text;charset=utf8")
	public void insert(@RequestBody MessageVO mv) {

		List<Integer> list = mv.getReceiverList();

		if (list.isEmpty()) {
			System.out.println("List is empty");
		} else {

			for (int i = 0; i < list.size(); i++) {

				if (list.get(i) == null) {
					list.remove(i);
				}
			}
		}

		List<MessageVO> msg = new ArrayList<MessageVO>();

		for (int i = 0; i < list.size(); i++) {

			if (list.get(i) != null) {
				MessageVO mv1 = new MessageVO();
				mv1.setContext(mv.getContext());
				mv1.setReadChk(1);
				mv1.setReceiver(list.get(i));
				mv1.setTime(ms.getTime());
				mv1.setSender(mv.getSender());
				mv1.setTeamId(mv.getTeamId());
				msg.add(mv1);

			}
		}

		int result = ms.receiverMessage(msg);
		String resultmsg = result == 0 ? "전송실패" : "전송성공";
		System.out.println("결과 : " + resultmsg);
	}

	@ResponseBody
	@RequestMapping(value = "alram/{memberId}/", method = RequestMethod.GET, produces = "applcation/text;charset=utf8")
	public String Chk(@RequestBody @PathVariable("memberId") int memberId) {

		List<MessageVO> Chk = ms.selectNotLead(memberId);
		
		String result = Chk.size() +"";
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "toggleChange/{toggleDB}/{teamId}/", method = RequestMethod.GET, produces = "applcation/text;charset=utf8")
	public void toggleChange(@RequestBody @PathVariable("toggleDB") int toggleDB ,@PathVariable("teamId") int teamId) {
		int toggleChk = 0;
		
		StudyVO sv = new StudyVO();
		sv.setTeamId(teamId);
		
		switch (toggleDB) {
		case 1:
			toggleChk = 1;
			sv.setTeamPublic(toggleChk);
			break;
		case 0:
			toggleChk = 0;
			sv.setTeamPublic(toggleChk);
			break;
		}
		
		int result = ss.toggleChange(sv);
		String resultChk = result == 1 ? "변경완료" : "실패";
		System.out.println(resultChk);
	}
}