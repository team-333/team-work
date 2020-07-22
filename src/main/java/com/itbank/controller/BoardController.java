package com.itbank.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itbank.service.BoardService;
import com.itbank.vo.BoardVO;
import com.itbank.vo.PageVO;

@RestController
public class BoardController {
	
	@Autowired private BoardService bs;
	
	@RequestMapping(value = "/selectBoard", produces = "application/text; charset=UTF8;")
	public String selectBoard(PageVO param) throws JsonProcessingException {
		String json = null;
		ObjectMapper mapper = new ObjectMapper();
		
		int page = param.getPage();
		
		if(page == 1) {
			param.setStartNum(1);
			param.setEndNum(5);
		}
		else {
			param.setStartNum(5 * (page - 1) + 1);
			param.setEndNum(page * 5);
		}
		
		List<BoardVO> list = bs.selectOne(param); 
		json = mapper.writeValueAsString(list);
		
		return json;
		
		
	}
	
	@RequestMapping("/insertBoard")
	public String insertBoard(@RequestParam Map<String, String> param) {
		bs.insert(param);
		return "안녕";
	}
}
