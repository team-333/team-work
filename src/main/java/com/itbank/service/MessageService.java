package com.itbank.service;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itbank.dao.MessageDAO;
import com.itbank.vo.MessageVO;

@Service
public class MessageService {
	@Autowired private MessageDAO md;

	public List<MessageVO> selectNotLead(int memberId) {
		return md.selectNotLead(memberId);
		
	}

	public int receiverMessage(List<MessageVO> msg) {
	
		return md.receiverMessage(msg);
	}
	
	
	public String getTime() {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yy년 MM월 dd일 HH:mm E요일");
		String time = dateFormat.format(date);
		
		return time;
	}

	
	public List<MessageVO> teamSearchMessage(int teamId) {
		
			
		return md.teamSearchMessage(teamId);
	}

	public List<MessageVO> memberSearchMessage(int memberId) {
		
		
		return md.memberSearchMessage(memberId);
	}

	public void deleteMsg(List<MessageVO> msg) {
		md.deleteMsg(msg);
		
	}

	public void readChk(int msgId) {
		md.readChk(msgId);
		
	}

	public List<MessageVO> senderSearchMessage(int memberId) {
		// TODO Auto-generated method stub
		return  md.senderSearchMessage(memberId);
	}
	
	
}
