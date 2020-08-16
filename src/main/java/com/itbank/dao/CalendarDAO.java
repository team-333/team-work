package com.itbank.dao;

import java.util.HashMap;
import java.util.List;

import com.itbank.vo.CalendarVO;

public interface CalendarDAO {

	int insertList(CalendarVO vo);

	List<CalendarVO> selectList(CalendarVO vo);

	int updateList(CalendarVO vo);

	int deleteList(CalendarVO vo);

	List<CalendarVO> selectOne(CalendarVO vo);
	
}
