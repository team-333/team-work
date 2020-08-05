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

}
