package com.itbank.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itbank.vo.BoardVO;
import com.itbank.vo.PageVO;
import com.itbank.dao.BoardDAO;

@Service
public class BoardService {
		
	@Autowired private BoardDAO bd;
	
	public List<BoardVO> selectOne(PageVO param) {
		return bd.selectOne(param);
	}

	public void insert(Map<String, String> param) {
		bd.insert(param);
	}

	
}
