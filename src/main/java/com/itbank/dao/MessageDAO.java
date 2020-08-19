package com.itbank.dao;

import java.util.List;

import com.itbank.vo.AlarmVO;
import com.itbank.vo.MessageVO;

public interface MessageDAO {

	List<MessageVO> selectNotLead(int memberId);

	int receiverMessage(List<MessageVO> msg);

	List<MessageVO> teamSearchMessage(int teamId);

	List<MessageVO> memberSearchMessage(int memberId);

	void deleteMsg(List<MessageVO> msg);

	int readChk(int msgId);

	List<MessageVO> senderSearchMessage(int memberId);

	int alarmInsert(List<AlarmVO> alarm);

	List<AlarmVO> selectAlarm(int memberId);

	
}
