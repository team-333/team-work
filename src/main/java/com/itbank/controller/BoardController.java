package com.itbank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.itbank.service.BoardService;
import com.itbank.vo.BoardVO;

@RestController
@RequestMapping("board")
public class BoardController {
	
	@Autowired private BoardService bs;
	
	@RequestMapping({"/", ""})
	public ModelAndView board() throws Exception {
		ModelAndView mav = new ModelAndView("board");
		List<BoardVO> list = bs.selectAll();
		mav.addObject("list", list);
		return mav;
	}
	
//	@RequestMapping
//	public String board_insert() {
//		
//	}
}
