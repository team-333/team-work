package com.itbank.dao;

import java.util.List;

import com.itbank.vo.BoardVO;

public interface BoardDAO {
	List<BoardVO> selectAll();
}
