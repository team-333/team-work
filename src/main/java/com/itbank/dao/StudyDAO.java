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


	
	StudyVO studydeleteChk(int teamId);

	List<Integer> selectTeamId(int memberId);
	
	List<StudyVO> searchedByName(String query);
	
	List<StudyVO> searchedByTag(int tagId);
	
	
	// 태그
	List<TagVO> selectTagAll();
	
	int insertTag(String tagName);
	
	int maxTagNum();
	
	int insertTeamTag(TeamTagVO ttvo);
	
	TagVO selectTag(String tagName);

	List<TagVO> selectStudyTag(int teamId);
	
	List<StudyVO> schedule(String deleteChkTime);

	int boardCommentDelete(int teamId);

	int boardDelete(int teamId);

	int memberTeamDelete(int teamId);

	int waitDelete(int teamId);

	int teamTagDelete(int teamId);

	int teamDelete(int teamId);

	void notDelete(int teamId);

}
