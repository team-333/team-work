package com.itbank.dao;

import com.itbank.vo.MembersVO;


public interface MembersDAO {

	
	MembersVO selectMembers(MembersVO vo);
	
	MembersVO emailcheck(String email);

	int insertMembers(MembersVO vo);

	void updatePw(MembersVO vo);
	
	MembersVO selectMember(String email);

	void updateTitle(MembersVO vo);

	void updateContext(MembersVO vo);

	void updateUsername(MembersVO vo);

	void updateEmail(MembersVO vo);

	void updatePassword(MembersVO vo);

	MembersVO checkPassword(MembersVO vo);


}
