package com.itbank.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itbank.service.BoardService;
import com.itbank.vo.BoardVO;
import com.itbank.vo.MembersVO;
import com.itbank.vo.PageVO;

@RestController
@RequestMapping("study/")
public class BoardController {
	
	@Autowired private BoardService bs;
	
	@RequestMapping(value = "selectBoard/", produces = "application/text; charset=UTF8;")
	public String selectBoard(PageVO param) throws JsonProcessingException {
		ArrayList<Object> arr = new ArrayList<Object>();
		
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
		
		List<BoardVO> board_list = bs.selectOne(param);		// 게시물 목록 저장
		List<String> img_list = new ArrayList<String>();	// 프로필 사진 저장
		
		// 프로필 사진 가져오기
		for(int i=0; i < board_list.size(); i++) {
			int memberId = board_list.get(i).getMemberid();
			img_list.add(bs.selectImg(memberId));
		}
		
		arr.add(board_list);
		arr.add(img_list);
		json = mapper.writeValueAsString(arr);
		
		return json;
	}
	
	@RequestMapping(value = "insertBoard/", produces = "application/text; charset=UTF8;")
	public String insertBoard(BoardVO param, HttpSession hss) {
		MembersVO login = (MembersVO) hss.getAttribute("login");
		
		if(login == null) { return "로그인이 필요합니다."; }
		
		param.setMemberid(login.getMemberId());
		param.setWriter(login.getUsername());
		
		if(teamCheck(param.getTeamid(), hss) == "허용") {
			bs.insert(param);
			return "등록 성공";
		}
		else {
			return "접근권한 거부";
		}
		
	}
	
	@RequestMapping(value = "deleteBoard/", produces = "application/text; charset=UTF8;" )
	public String deleteBoard(PageVO param, HttpSession hss) {
		if(boardCheck(param, hss).equals("허용")) {
			bs.delete(param);
			return "삭제성공";
		}
		else {
			return "삭제실패";
		}
	}
	
	@RequestMapping(value = "updateBoard/", produces = "application/text; charset=UTF8;" )
	public String updateBoard(BoardVO param, HttpSession hss) {
		PageVO vo = new PageVO();
		vo.setPage(param.getNum());
		vo.setTeamid(param.getTeamid());
		
		int result = 0;
		
		if(boardCheck(vo, hss).equals("허용")) {
			bs.update(param);
		}
		System.out.println(result);
		return "허용";
	}

	
	@RequestMapping(value = "teamCheck/", produces = "application/text; charset=UTF8;")
	public String teamCheck(int teamid ,HttpSession hss) {
		MembersVO login = (MembersVO) hss.getAttribute("login");
		
		List<Integer> checkList = bs.selectCheck(teamid);
		
		if(login == null) { return "세션이 존재하지 않습니다."; }
		
		return checkList.indexOf(login.getMemberId()) != -1 ? "허용" : "거부";
	}
	
	@RequestMapping(value = "boardCheck/", produces = "application/text; charset=UTF8;")
	public String boardCheck(PageVO param, HttpSession hss) {
		MembersVO login = (MembersVO) hss.getAttribute("login");
		int boardID = bs.selectBoardCheck(param);
		
		if(login == null) { return "세션이 존재하지 않습니다."; }
		
		// 사용자1 : 본인, 사용자2 : 타인, 관리자 등..
		return login.getMemberId() == boardID ? "허용" : "거부";
	}
	
	
	
}
