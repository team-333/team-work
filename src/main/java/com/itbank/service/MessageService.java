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


}