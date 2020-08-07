package com.itbank.dao;

import java.util.List;

import com.itbank.vo.MemberTeamVO;
import com.itbank.vo.MembersVO;


public interface MembersDAO {

	
	MembersVO selectMembers(MembersVO vo);
	
	MembersVO emailcheck(String email);

	int insertMembers(MembersVO vo);

	void updatePw(MembersVO vo);
	
	MembersVO selectMember(int memberId);

	void updateTitle(MembersVO vo);

	void updateContext(MembersVO vo);

	void updateUsername(MembersVO vo);

	void updateEmail(MembersVO vo);

	void updatePassword(MembersVO vo);

	MembersVO checkPassword(MembersVO vo);

	List<MembersVO> MemberList(List<MemberTeamVO> memberTeam);

	List<MemberTeamVO> TeamList(int teamId);

	List<MemberTeamVO> waitMember(int teamId);

	List<MembersVO> waitMemberLIst(List<MemberTeamVO> waitMember);

	int joinTeam(MemberTeamVO mt);

	int notTeam(MemberTeamVO mt);

	MemberTeamVO searchMemberTeam(MemberTeamVO mtv);

	MemberTeamVO searchWait(MemberTeamVO mtv);
	
	int signout(MemberTeamVO mt);
}
