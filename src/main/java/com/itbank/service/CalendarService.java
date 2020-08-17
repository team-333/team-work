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

	public List<CalendarVO> insertTodoList(HashMap<String, String> param, int teamNum) {
		CalendarVO vo = new CalendarVO();
		System.out.println("service : " + param.toString());
		String inherence = UUID.randomUUID().toString().replace("-", "");
		vo.setTitle(param.get("title"));
		vo.setRegistDate(param.get("registDate"));
		vo.setRegTime(param.get("regTime"));
		vo.setContext(param.get("context"));
		vo.setInherence(inherence);
		vo.setTeamNum(teamNum);
		System.out.println("vo : " + vo.getTitle() + ", " + vo.getRegistDate() + ", " + vo.getRegTime() + ", " + vo.getContext() + ", " + vo.getInherence() + ", " + vo.getTeamNum());
		dao.insertList(vo);
		return dao.selectList(vo);
	}

	public List<CalendarVO> selectList(String checkYearMonth, int teamNum) {
		CalendarVO vo = new CalendarVO();
		vo.setRegistDate(checkYearMonth);
		vo.setTeamNum(teamNum);
		return dao.selectList(vo);
	}

	public List<CalendarVO> updateTodoList(HashMap<String, String> param, int teamNum) {
		CalendarVO vo = new CalendarVO();
		vo.setTitle(param.get("title"));
		vo.setRegistDate(param.get("registDate"));
		vo.setRegTime(param.get("regTime"));
		vo.setContext(param.get("context"));
		vo.setInherence(param.get("inherence"));
		vo.setTeamNum(teamNum);
		dao.updateList(vo);
		return dao.selectList(vo);
	}

	public List<CalendarVO> deleteTodoList(ArrayList<String> checkedList, int teamNum) {
		for (String check : checkedList) {	
			CalendarVO vo = new CalendarVO();
			vo.setInherence(check);
			vo.setTeamNum(teamNum);
			dao.deleteList(vo);
		}
		return null;
	}

	public List<CalendarVO> selectOne(String inherence, int teamNum) {
		CalendarVO vo = new CalendarVO();
		vo.setInherence(inherence);
		vo.setTeamNum(teamNum);
		return dao.selectOne(vo);
	}



}