package com.itbank.dao;





import com.itbank.vo.MembersVO;


public interface MembersDAO {

	
	MembersVO selectMembers(MembersVO vo);
	
	MembersVO emailcheck(String email);

	int insertMembers(MembersVO vo);

	void updatePw(MembersVO vo);

 
	


}
