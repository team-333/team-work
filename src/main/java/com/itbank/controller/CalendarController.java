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
	public List<CalendarVO> selectList(@PathVariable("param") String checkYearMonth, @PathVariable("teamId") int teamId){
		System.out.println("checkYearMonth : " + checkYearMonth);
		System.out.println("teamId : " + teamId);
		List<CalendarVO> loadList = cs.selectList(checkYearMonth, teamId);
		System.out.println(loadList);
		return loadList;
	}

	@RequestMapping(value = "study/calenda/{teamId}/insert/", produces = "application/json; charset=utf-8")
	public int insertList(@RequestBody CalendarVO param,  @PathVariable("teamId") int teamId) {
		System.out.println("controller : " + param.toString()); // 내용 확인
		System.out.println("teamId : " + teamId);

		param.setteamId(teamId);

		return cs.insertTodoList(param, teamId);
	}

	@RequestMapping(value = "study/calenda/{teamId}/updateForm/{param}/")
	public List<CalendarVO> updateFormList(@PathVariable("param")String inherence, @PathVariable("teamId") int teamId){
		System.out.println("inherence : " + inherence);
		System.out.println("teamId : " + teamId);
		List<CalendarVO> todoList = cs.selectOne(inherence, teamId);
		System.out.println(todoList);
		return todoList;
	}

	@RequestMapping(value = "study/calenda/{teamId}/update/", produces = "application/json; charset=utf-8")
	public int updateList(@RequestBody HashMap<String, String> param, @PathVariable("teamId") int teamId) {
		System.out.println("teamId : " + teamId);
		System.out.println(param);
		return cs.updateTodoList(param, teamId);
	}

	@RequestMapping(value = "study/calenda/{teamId}/delete/", produces = "application/json; charset=utf-8")
	public String deleteList(@RequestBody ArrayList<String> checkedList, @PathVariable("teamId") int teamId) {
		System.out.println("checkedList : " + checkedList);
		System.out.println("teamId : " + teamId);
		return cs.deleteTodoList(checkedList, teamId);
	}

	@RequestMapping(value = "study/calenda/{teamId}/selectOneBoard/{param}/")
	public CalendarVO selectOneBoard(@PathVariable("param") String inherence, @PathVariable("teamId") int teamId) {
		System.out.println("inherence : " + inherence);
		System.out.println("teamId : " + teamId);
		return cs.selectOneBoard(inherence, teamId);
	}


}