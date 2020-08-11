package com.itbank.dao;

import java.util.List;

import com.itbank.vo.MessageVO;

public interface MessageDAO {

	List<MessageVO> selectNotLead(int memberId);

	int receiverMessage(List<MessageVO> msg);

}
