package com.itbank.dao;

import java.util.List;

import com.itbank.vo.BoardCommentVO;
import com.itbank.vo.BoardVO;
import com.itbank.vo.PageVO;

public interface BoardDAO {
	List<BoardVO> selectOne(PageVO param);
	int insert(BoardVO param);
	String selectImg(int memberId);
	int seclectGroupLeader(int teamid);
	List<Integer> selectGroupList(int teamid);
	int selectBoardCheck(PageVO param);
	int delete(PageVO param);
	int update(BoardVO param);
	List<BoardCommentVO> selectComment(PageVO param);
	int insertComment(Object parm);
	int selectCommentCount(PageVO param);
	int selectCommentCheck(PageVO param);
	int deleteComment(PageVO param);
	int updateComment(BoardCommentVO param);
}
