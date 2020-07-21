package com.itbank.dao;

import java.util.List;
import java.util.Map;

import com.itbank.vo.BoardVO;
import com.itbank.vo.PageVO;

public interface BoardDAO {
	List<BoardVO> selectOne(PageVO param);
	void insert(Map<String, String> param);
}
