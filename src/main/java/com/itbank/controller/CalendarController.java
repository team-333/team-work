package com.itbank.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.itbank.service.CalendarService;
import com.itbank.service.MembersService;
import com.itbank.service.StudyService;
import com.itbank.vo.CalendarVO;
import com.itbank.vo.StudyVO;

@RestController
@CrossOrigin
public class CalendarController {

	@Autowired private CalendarService cs;
	@Autowired private StudyService ss;
	@Autowired private MembersService ms;

	@RequestMapping(value = "study/calenda/{teamId}/")
	public ModelAndView calenda(@PathVariable int teamId) {
		ModelAndView mav = new ModelAndView("calenda");
		StudyVO team = ss.selectStudy(teamId);

		System.out.println("팀아디 : " + teamId);

		mav.addObject("teamInfo", team);
		mav.addObject("captain", ms.selectMember(team.getDelegate()));

		return mav;
	}

	@RequestMapping(value = "study/calenda/{teamId}/select/{param}/")
	public List<CalendarVO> selectList(@PathVariable("param") String checkYearMonth, @PathVariable("teamId") int teamNum){
		System.out.println("checkYearMonth : " + checkYearMonth);
		System.out.println("teamNum : " + teamNum);
		List<CalendarVO> loadList = cs.selectList(checkYearMonth, teamNum);
		System.out.println(loadList);
		return loadList;
	}

	@RequestMapping(value = "study/calenda/{teamId}/insert/", produces = "application/json; charset=utf-8")
	public List<CalendarVO> insertList(@RequestBody HashMap<String, String> param,  @PathVariable("teamId") int teamNum) {
		System.out.println("controller : " + param.toString()); // 내용 확인
		System.out.println("teamNum : " + teamNum);
		List<CalendarVO> todoList = cs.insertTodoList(param, teamNum);
		return todoList;
	}

	@RequestMapping(value = "study/calenda/{teamId}/updateForm/{param}/")
	public List<CalendarVO> updateFormList(@PathVariable("param")String inherence, @PathVariable("teamId") int teamNum){
		System.out.println("inherence : " + inherence);
		System.out.println("teamNum : " + teamNum);
		List<CalendarVO> todoList = cs.selectOne(inherence, teamNum);
		System.out.println(todoList);
		return todoList;
	}

	@RequestMapping(value = "study/calenda/{teamId}/update/", produces = "application/json; charset=utf-8")
	public List<CalendarVO> updateList(@RequestBody HashMap<String, String> param, @PathVariable("teamId") int teamNum) {
		System.out.println("teamNum : " + teamNum);
		List<CalendarVO> todoList = cs.updateTodoList(param, teamNum);
		return todoList;
	}

	@RequestMapping(value = "study/calenda/{teamId}/delete/", produces = "application/json; charset=utf-8")
	public List<CalendarVO> deleteList(@RequestBody ArrayList<String> checkedList, @PathVariable("teamId") int teamNum) {
		System.out.println("checkedList : " + checkedList);
		System.out.println("teamNum : " + teamNum);
		cs.deleteTodoList(checkedList, teamNum);
		return null;
	}


}