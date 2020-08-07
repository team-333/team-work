package com.itbank.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

//만들고있는코드
import com.itbank.vo.MessageVO;



@Controller
public class AjaxController {

	@ResponseBody
	@RequestMapping(value = "message/",method = RequestMethod.POST, produces = "applcation/text;charset=utf8")
	public void insert(@RequestBody MessageVO mv) {

		System.out.println(mv.getContext());
		System.out.println(mv.getReceiver());
		System.out.println(mv.getTeamId());
		System.out.println(mv.getTime());
		System.out.println(mv.getSenderList());
	}
}
