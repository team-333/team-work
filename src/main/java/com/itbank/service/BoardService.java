package com.itbank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itbank.vo.BoardVO;
import com.itbank.dao.BoardDAO;

@Service
public class BoardService {
		
	@Autowired private BoardDAO bd;
	
	public List<BoardVO> selectAll(){return bd.selectAll();	}
	
}
