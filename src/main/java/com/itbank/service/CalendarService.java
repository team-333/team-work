package com.itbank.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itbank.dao.CalendarDAO;
import com.itbank.vo.CalendarVO;

@Service
public class CalendarService {

	@Autowired private CalendarDAO dao;

	public int insertTodoList(CalendarVO param, int teamId) {
		return dao.insertList(param);
	}

	public List<CalendarVO> selectList(String checkYearMonth, int teamId) {
		CalendarVO vo = new CalendarVO();
		vo.setRegistDate(checkYearMonth);
		vo.setteamId(teamId);
		return dao.selectList(vo);
	}

	public int updateTodoList(HashMap<String, String> param, int teamId) {
		CalendarVO vo = new CalendarVO();
		vo.setTitle(param.get("title"));
		vo.setRegistDate(param.get("registDate"));
		vo.setRegTime(param.get("regTime"));
		vo.setContext(param.get("context"));
		vo.setInherence(param.get("inherence"));
		vo.setteamId(teamId);
		return dao.updateList(vo);
	}

	public String deleteTodoList(ArrayList<String> checkedList, int teamId) {
		ArrayList<Integer> succ = new ArrayList<Integer>();
		String state = null;
		for (String check : checkedList) {	
			CalendarVO vo = new CalendarVO();
			vo.setInherence(check);
			vo.setteamId(teamId);
			succ.add(dao.deleteList(vo));
			System.out.println(succ);
		}

		System.out.println("succ" + succ);

		for(int i = 0; i < succ.size(); i++) {
			if(succ.get(i) > 0) {
				state = "성공";
			}
			else {
				state = "실패";
				return state;
			}
		}
		return state;
	}

	public List<CalendarVO> selectOne(String inherence, int teamId) {
		CalendarVO vo = new CalendarVO();
		vo.setInherence(inherence);
		vo.setteamId(teamId);
		return dao.selectOne(vo);
	}

	public CalendarVO selectOneBoard(String inherence, int teamId) {
		CalendarVO vo = new CalendarVO();
		vo.setInherence(inherence);
		vo.setteamId(teamId);
		return dao.selectOneBoard(vo);
	}



}