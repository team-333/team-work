package com.itbank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itbank.dao.BoardDAO;
import com.itbank.vo.BoardCommentVO;
import com.itbank.vo.BoardVO;
import com.itbank.vo.PageVO;

@Service
public class BoardService {
		
	@Autowired private BoardDAO bd;
	
	public List<BoardVO> selectOne(PageVO param) {
		return bd.selectOne(param);
	}

	public int insert(BoardVO param) {
		return bd.insert(param);
	}

	public int delete(PageVO param) {
		return bd.delete(param);
	}
	
	public int update(BoardVO param) {
		return bd.update(param);
	}
	
	public String selectImg(int memberId) {
		return bd.selectImg(memberId);
	}
	
	public int seclectGroupLeader(int teamid) {
		return bd.seclectGroupLeader(teamid);
	}

	public List<Integer> selectGroupList(int teamid) {
		return bd.selectGroupList(teamid);
	}

	public int selectBoardCheck(PageVO param) {
		return bd.selectBoardCheck(param);
	}

	public int selectCommentCheck(PageVO param) {
		return bd.selectCommentCheck(param);
	}
	
	public List<BoardCommentVO> selectComment(PageVO param) {
		return bd.selectComment(param);
	}

	public int insertComment(BoardCommentVO param) {
		return bd.insertComment(param);
	}

	public int selectCommentCount(PageVO param) {
		return bd.selectCommentCount(param);
	}

	public int deleteComment(PageVO param) {
		return bd.deleteComment(param);
	}

	public int updateComment(BoardCommentVO param) {
		return bd.updateComment(param);
	}

	public String selectInherence(PageVO param) {
		return bd.selectInherence(param);
	}

	public int updateNotice(PageVO param) {
		return bd.updateNotice(param); 
	}

	public int selectCountComment(PageVO param) {
		return bd.selectCountComment(param);
	}




}