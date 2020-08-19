package com.itbank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itbank.dao.BoardDAO;
import com.itbank.vo.BoardVO;
import com.itbank.vo.PageVO;

@Service
public class BoardService {
		
	@Autowired private BoardDAO bd;
	
	public List<BoardVO> selectOne(PageVO param) {
		return bd.selectOne(param);
	}

	public void insert(BoardVO param) {
		bd.insert(param);
	}

	public void delete(PageVO param) {
		bd.delete(param);
	}
	
	public int update(BoardVO param) {
		return bd.update(param);
	}
	
	public String selectImg(int memberId) {
		return bd.selectImg(memberId);
	}

	public List<Integer> selectCheck(int teamid) {
		return bd.selectCheck(teamid);
	}

	public int selectBoardCheck(PageVO param) {
		return bd.selectBoardCheck(param);
	}


}