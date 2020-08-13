package com.itbank.dao;

import java.util.List;

import com.itbank.vo.MemberTeamVO;
import com.itbank.vo.StudyVO;
import com.itbank.vo.TagVO;
import com.itbank.vo.TeamTagVO;

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
	
	int studyDelete(StudyVO sv);

	int schedule(String deleteChkTime);

	StudyVO studydeleteChk(int teamId);

	List<StudyVO> searchStudylist(String text);

	List<Integer> selectTeamId(int memberId);
	
	List<StudyVO> searchText(String searchtext);
	
	
	
	// 태그
	List<TagVO> selectTagAll();
	
	int insertTag(String tagName);
	
	int maxTagNum();
	
	int insertTeamTag(TeamTagVO ttvo);
	
	TagVO selectTag(String tagName);
}
