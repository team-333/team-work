package com.itbank.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.itbank.service.MembersService;
import com.itbank.service.MessageService;
import com.itbank.vo.MembersVO;
import com.itbank.vo.MessageVO;


@Controller
public class MyMessageController {

	@Autowired private MessageService messageService;
	@Autowired private MembersService membersService;

	@RequestMapping(value="mymessage/{memberId}/{msgType}")
	public ModelAndView mymessage(@PathVariable int memberId,@PathVariable String msgType) {
		ModelAndView mav = new ModelAndView("mymessage");
		
		List<MessageVO> msgList = new ArrayList<MessageVO>();
		List<MessageVO> msgList2 = new ArrayList<MessageVO>();
		
		switch (msgType) {
		case "receiver":
			msgList =  messageService.memberSearchMessage(memberId);
			msgList2 =  messageService.senderSearchMessage(memberId);
			
			 mav.addObject("msgSize", msgList.size());
			 mav.addObject("msgSize2", msgList2.size());
			
			mav.addObject("msgType", "receiver");
			break;

		case "sender":
			 msgList =  messageService.senderSearchMessage(memberId);
			 msgList2 =  messageService.memberSearchMessage(memberId);
			 
			 mav.addObject("msgSize", msgList2.size());
			 mav.addObject("msgSize2", msgList.size());
			 
			 mav.addObject("msgType", "sender");
			break;
		}
		
		
		List<MessageVO> newMsgList = new ArrayList<MessageVO>();
		
		for(int i = 0; i < msgList.size(); i++) {
			MembersVO mv = new MembersVO();
			MessageVO msg = msgList.get(i);
			mv = membersService.selectMember(msgList.get(i).getSender());
			
			msg.setUserName(mv.getUsername());
			newMsgList.add(i, msg);
		}
		
		
		
	
		mav.addObject("message", newMsgList);
		
		return mav;
	}
}