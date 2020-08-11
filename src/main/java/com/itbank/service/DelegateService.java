package com.itbank.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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

	
	public List<MemberTeamVO> memberTeam(int teamId){
		
		return memberDAO.TeamList(teamId);
	}
	

	public List<MembersVO> MemberTeamList(List<MemberTeamVO> memberTeam) {
	
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


	public int studyDelete(int teamId) {
		
		StudyVO sv = new StudyVO();
		
		Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yy년 MM월 dd일");
       
        System.out.println("current: " + dateFormat.format(cal.getTime()));

 
        cal.add(Calendar.DATE, 1);
        String time = dateFormat.format(cal.getTime());
        System.out.println("after: " + time);
		
	
		
		sv.setTeamId(teamId);
		sv.setDeleteTime(time);
		
		return studyDAO.studyDelete(sv);
	}

	//삭제중인지 확인
	public StudyVO studydeleteChk(int teamId) {
		
		return studyDAO.studydeleteChk(teamId);
	}



 
	
	
}
