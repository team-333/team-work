package com.itbank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.itbank.dao.MembersDAO;
import com.itbank.dao.StudyDAO;
import com.itbank.vo.MemberTeamVO;
import com.itbank.vo.MembersVO;
import com.itbank.vo.StudyVO;


@Service
public class DelegateService {

	@Autowired private MembersDAO memberDAO;
	@Autowired private StudyDAO studyDAO;



	public List<MembersVO> MemberTeamList(int teamId) {

		List<MemberTeamVO> memberTeam = memberDAO.TeamList(teamId);

		return 	memberDAO.MemberList(memberTeam);
	}


	public StudyVO team(int teamId) {

		return studyDAO.team(teamId);
	}


	public List<MembersVO> waiting(List<MemberTeamVO> waitMember) {
		return memberDAO.waitMemberLIst(waitMember); // memberID목록을 불러와 개개인의 정보를 불러온다.
	}



	// DB waiting에 teamId에 신청된 아이디목록을 불러온다.
	public List<MemberTeamVO> waitMember(int teamId){

		return memberDAO.waitMember(teamId);
	}


	// 승인시 memberTeam 테이블에 추가되는 코드
	public int joinMember(MemberTeamVO mt) {
		return memberDAO.joinTeam(mt);
	}

	// 승인과 관계없이 어떤 버튼을 누르던 waiting 테이블에서는 제거되는 코드
	public int deleteWating(MemberTeamVO mt) {
		return memberDAO.notTeam(mt);
	}


	// waiting 테이블 중복체크
	public MemberTeamVO searchWait(MemberTeamVO mtv) {

	    return memberDAO.searchWait(mtv);
	}

	// MemberTeam 테이블 중복체크
	public MemberTeamVO searchMemberTeam(MemberTeamVO mtv) {


		return memberDAO.searchMemberTeam(mtv);
	}






}