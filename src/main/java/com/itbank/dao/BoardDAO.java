package com.itbank.dao;

import java.util.List;

import com.itbank.vo.BoardVO;
import com.itbank.vo.PageVO;

public interface BoardDAO {
	List<BoardVO> selectOne(PageVO param);
	void insert(BoardVO param);
	String selectImg(int memberId);
	List<Integer> selectCheck(int teamid);
	int selectBoardCheck(PageVO param);
	void delete(PageVO param);
	int update(BoardVO param);
}
