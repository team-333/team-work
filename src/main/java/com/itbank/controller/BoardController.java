package com.itbank.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itbank.service.BoardService;
import com.itbank.service.DelegateService;
import com.itbank.service.MessageService;
import com.itbank.vo.AlarmVO;
import com.itbank.vo.BoardCommentVO;
import com.itbank.vo.BoardVO;
import com.itbank.vo.MemberTeamVO;
import com.itbank.vo.MembersVO;
import com.itbank.vo.PageVO;

@RestController
@RequestMapping("study/")
public class BoardController {
	@Autowired private BoardService bs;
	@Autowired private DelegateService ds;
	@Autowired private MessageService ms;
	
	// 게시물 목록
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
	
	// 게시물 등록
	   @RequestMapping(value = "insertBoard/", produces = "application/text; charset=UTF8;")
	   public String insertBoard(BoardVO param, HttpSession hss) throws JsonProcessingException {
	      MembersVO login = (MembersVO) hss.getAttribute("login");
	      String inherence = UUID.randomUUID().toString().replace("-", "");
	      String teamCheck = teamCheck(param.getTeamid(), hss);
	      
	      ObjectMapper mapper = new ObjectMapper();
	      HashMap<String, String> map = new HashMap<String, String>();
	      
	      
	      List<MemberTeamVO> listmt = ds.memberTeam(param.getTeamid());  
	      List<AlarmVO> alarm = new ArrayList<AlarmVO>();
	      if(listmt.size() != 0) {
	         for(int i =0; i< listmt.size(); i++) {
	            
	            AlarmVO av = new AlarmVO();
	            av.setContext(login.getUsername() + "님이 게시물을 작성했습니다.");
	            av.setMovePage("/study/" + param.getTeamid()  + "/");
	            av.setReadChk(1);
	            av.setTime(ms.getTime());
	            av.setReceiver(listmt.get(i).getMemberId());
	            alarm.add(av);
	         }
	         
	      }
	   
	      
	      int alramChk = ms.alarmInsert(alarm);
	      System.out.println(alramChk);
	      
	      
	      
	      param.setMemberid(login.getMemberId());
	      param.setWriter(login.getUsername());
	      param.setInherence(inherence);
	      
	      if(param.getContext() == null)   param.setContext("");
	      
	      // 0: 로그아웃, (-1, 1): 등록성공여부
	      if(teamCheck == "0")                         return "0";
	      else if(teamCheck == "1" || teamCheck == "2") {
	         map.put("result", (bs.insert(param) + ""));
	         map.put("inherence", inherence);
	         
	         return mapper.writeValueAsString(map);
	      }
	      else                                     return "-1";
	   }
	
	// 게시물 삭제
	@RequestMapping(value = "deleteBoard/", produces = "application/text; charset=UTF8;" )
	public String deleteBoard(PageVO param, HttpSession hss) {
		String boardCheck = boardCheck(param, hss) ;
		String teamCheck = teamCheck(param.getTeamid(), hss);	// 그룹장 권한 확인
		
		if(boardCheck == "0" || teamCheck == "0")	return "0";
		if(boardCheck == "1" || teamCheck == "2")	return bs.delete(param) > 0 ? "1" : "-1";
			
		return "0";
	}
		
	// 게시물 수정
	@RequestMapping(value = "updateBoard/", produces = "application/text; charset=UTF8;" )
	public String updateBoard(BoardVO param, HttpSession hss) throws JsonProcessingException {
		PageVO vo = new PageVO();
		ObjectMapper mapper = new ObjectMapper();
		HashMap<String, String> map = new HashMap<String, String>();
		
		vo.setPage(param.getNum());
		vo.setTeamid(param.getTeamid());
		
		String boardCheck = boardCheck(vo, hss);
		String inherence = boardInherence(vo, hss);
		
		param.setInherence(inherence);
		
		// 0: 게시물 확인, 1: 수정성공, -1: 수정실패
		if(boardCheck == "1" || boardCheck == "2") {
			map.put("result", ((bs.update(param) > 0) ? "1" : "-1"));
			map.put("inherence", inherence);

			return mapper.writeValueAsString(map);
		}
		
		return "0";
	}
	
	// 공지 등록
		@RequestMapping(value = "updateNotice/", produces = "application/text; charset=UTF8;" )
		public String updateNotice(PageVO param, int type, HttpSession hss) {
			String teamCheck = teamCheck(param.getTeamid(), hss);

			if(teamCheck == "2")	return bs.updateNotice(param) == 1 ? "1" : "2";
			else					return teamCheck;
		}
	
	// 댓글 목록
	@RequestMapping(value = "selectComment/", produces = "application/text; charset=UTF8;")
	public String selectComment(PageVO param) throws JsonProcessingException {
		ArrayList<Object> arr = new ArrayList<Object>();
		
		String json = null;
		ObjectMapper mapper = new ObjectMapper();
		
		int page = param.getPage();

		if(page == 1) {
			param.setStartNum(1);
			param.setEndNum(3);
		}
		else {
			param.setStartNum(3 * (page - 1) + 1);
			param.setEndNum(page * 3);
		}
		
		List<BoardCommentVO> comment_list = bs.selectComment(param);		// 댓글 목록 저장
		List<String> img_list = new ArrayList<String>();	// 프로필 사진 저장
		
		
		// 프로필 사진 가져오기
		for(int i=0; i < comment_list.size(); i++) {
			int memberId = comment_list.get(i).getMemberid();
			img_list.add(bs.selectImg(memberId));
		}
		
		arr.add(comment_list);
		arr.add(img_list);
		arr.add(bs.selectCommentCount(param));

		json = mapper.writeValueAsString(arr);
		
		return json;
	}
	
	// 댓글 등록
	   @RequestMapping(value = "insertComment/", produces = "application/text; charset=UTF8;")
	   public String insertComment(BoardCommentVO param, HttpSession hss) {
	      MembersVO login = (MembersVO) hss.getAttribute("login");
	      String teamCheck = teamCheck(param.getTeamid(), hss);
	      
	      param.setMemberid(login.getMemberId());
	      param.setWriter(login.getUsername());
	      
	      AlarmVO av = new AlarmVO();
	      av.setContext(login.getUsername() + "님이 댓글을 작성했습니다.");
	      
	      av.setMovePage("/study/" + param.getTeamid()  + "/");
	   
	      av.setReadChk(1);
	      
	      av.setReceiver(login.getMemberId());
	      
	      av.setTime(ms.getTime());
	      List<AlarmVO> alarm = new ArrayList<AlarmVO>();
	      alarm.add(av);
	      
	      int alramChk = ms.alarmInsert(alarm);
	      System.out.println(alramChk);
	      
	      // 0: 로그아웃, (-1, 1): 등록성공여부
	      if(teamCheck == "0")                         return teamCheck;
	      else if(teamCheck == "1" || teamCheck == "2")      return bs.insertComment(param) + "";
	      else                                     return "-1";
	      
	   }
	
	// 댓글 삭제
	@RequestMapping(value = "deleteComment/", produces = "application/text; charset=UTF8;" )
	public String deleteComment(PageVO param, HttpSession hss) {
		String commentCheck = commentCheck(param, hss) ;
		String teamCheck = teamCheck(param.getTeamid(), hss);	// 그룹장 권한 확인
		
		if(commentCheck == "0" || teamCheck == "0")	return "0";
		if(commentCheck == "1" || teamCheck == "2")	return bs.deleteComment(param) > 0 ? "1" : "-1";
			
		return "0";
	}
	
	// 댓글 수정
	@RequestMapping(value = "updateComment/", produces = "application/text; charset=UTF8;" )
	public String updateComment(BoardCommentVO param, HttpSession hss) {
		PageVO vo = new PageVO();
		
		vo.setTeamid(param.getTeamid());
		vo.setPage(param.getNum());
		vo.setCmtPage(param.getCmtnum());
		
		String commentCheck = commentCheck(vo, hss);
		
		// 0: 댓글 확인, 1: 수정성공, -1: 수정실패
		if(commentCheck == "1" || commentCheck == "2")		return (bs.updateComment(param) > 0) ? "1" : "-1";
		
		return "0";
	}
	
	// 댓글 갯수
		@RequestMapping(value = "selectCountComment/", produces = "application/text; charset=UTF8;" )
		public String selectCountComment(PageVO param) {
			return bs.selectCountComment(param) + "";
		}
	
	// 그룹원 확인
	@RequestMapping(value = "teamCheck/", produces = "application/text; charset=UTF8;")
	public String teamCheck(int teamid ,HttpSession hss) {
		MembersVO login = (MembersVO) hss.getAttribute("login");
		int groupReader = bs.seclectGroupLeader(teamid);
		List<Integer> groupList = bs.selectGroupList(teamid); 
		
		// 0: 로그아웃, (-1, 1): 그룹원 여부, (-2, 2): 그룹장
		if(login == null)							return "0";
		else if(groupReader == login.getMemberId())	return "2";
		else										return (groupList.indexOf(login.getMemberId()) != -1) ? "1" : "-1";
	}
	
	// 게시물 확인
	@RequestMapping(value = "boardCheck/", produces = "application/text; charset=UTF8;")
	public String boardCheck(PageVO param, HttpSession hss) {
		MembersVO login = (MembersVO) hss.getAttribute("login");
		int groupReader = bs.seclectGroupLeader(param.getTeamid());
		int boardID = bs.selectBoardCheck(param);
		
		// 0: 로그아웃, (-1, 1): 그룹원 게시물 여부, (-2, 2): 그룹장 권한 게시물
		if(login == null)	return "0";
		else if(groupReader == login.getMemberId()) return  (login.getMemberId() == boardID) ? "2" : "-2";
		else										return  (login.getMemberId() == boardID) ? "1" : "-1";
	}
	
	// 댓글 확인
	@RequestMapping(value = "commentCheck/", produces = "application/text; charset=UTF8;")
	public String commentCheck(PageVO param, HttpSession hss) {
		System.out.println(param.getCmtPage());
		System.out.println(param.getTeamid());
		System.out.println(param.getPage());
		
		MembersVO login = (MembersVO) hss.getAttribute("login");
		int groupReader = bs.seclectGroupLeader(param.getTeamid());
		int commentID = bs.selectCommentCheck(param);
		
		
		// 0: 로그아웃, (-1, 1): 그룹원 게시물 여부, (-2, 2): 그룹장 권한 게시물
		if(login == null)	return "0";
		else if(groupReader == login.getMemberId()) return  (login.getMemberId() == commentID) ? "2" : "-2";
		else										return  (login.getMemberId() == commentID) ? "1" : "-1";
	}
	
	// 게시물 고유값
		@RequestMapping(value = "boardInherence/", produces = "application/text; charset=UTF8;")
		public String boardInherence(PageVO param, HttpSession hss) {
			return bs.selectInherence(param); 
		}
		
}