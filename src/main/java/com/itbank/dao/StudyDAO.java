package com.itbank.dao;

import java.util.List;

import com.itbank.vo.MemberTeamVO;
import com.itbank.vo.StudyVO;

public interface StudyDAO {
	
	int selectSeq();
	
	List<StudyVO> selectAllStudies();

	int insertStudy(StudyVO vo);
	
	int memberTeamInsert(MemberTeamVO mo);

	List<StudyVO> selectMemberStudies(int memberId);

	StudyVO selectStudy(int teamId);

	int waitingTeam(MemberTeamVO mo);

	StudyVO team(int teamId);
	
	int toggleChange(StudyVO sv);

	List<StudyVO> searchStudylist(String text);

	List<Integer> selectTeamId(int memberId);
	
	List<StudyVO> searchText(String searchtext);
}
