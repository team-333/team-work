package com.itbank.message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.itbank.service.DelegateService;
import com.itbank.service.StudyService;
import com.itbank.vo.MemberTeamVO;
import com.itbank.vo.MembersVO;
import com.itbank.vo.StudyVO;


public class EchoHandler extends TextWebSocketHandler {
	// 세션 리스트
	private List<WebSocketSession> sessionList = new ArrayList<WebSocketSession>();
	private List<WebSocketSession> sessionmemberList = new ArrayList<WebSocketSession>();
	private Map<Integer, WebSocketSession> userSessionsMap = new HashMap<Integer, WebSocketSession>();

	@Autowired private DelegateService ds;
	@Autowired private StudyService ss;

	// 클라이언트가 연결 되었을 때 실행
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {

		Map<String,Object> map = session.getHandshakeAttributes();



		sessionList.add(session);
		MembersVO login = (MembersVO)map.get("login");

		userSessionsMap.put(login.getMemberId() , session);


		System.out.println("=======최초 접속=======");
		System.out.println("접속된 아이디" + login.getMemberId());
		System.out.println("접속된 세션아이디" + session.getId());
		System.out.println("====================");

		WebSocketSession sess = userSessionsMap.get(login.getMemberId());

		sess.sendMessage(new TextMessage("connect"));

	}

	// 클라이언트가 웹소켓 서버로 메시지를 전송했을 때 실행
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {

		Map<String,Object> map = session.getHandshakeAttributes();
		String dearid = message.getPayload();

		sessionList.add(session);
		MembersVO login = (MembersVO)map.get("login");		
		userSessionsMap.put(login.getMemberId() , session);
		System.out.println("=======특정 메시지받음======");
		System.out.println(dearid);



		List<Integer> msgChk = new ArrayList<Integer>();

		String msg[] = dearid.split(",");

		if(msg[0].equals("읽기완료")) {
			
			
			WebSocketSession sess = userSessionsMap.get(login.getMemberId());
			sess.sendMessage(new TextMessage(login.getUsername() + "메시지 확인완료."));
				
			
		}
		
		
		else if(msg[0].equals("게시물 등록")) {
			System.out.println("게시물등록!!");
			List<MemberTeamVO> mt =  ds.memberTeam(Integer.parseInt(msg[1]));
			StudyVO so = ss.selectStudy(Integer.parseInt(msg[1]));
			if(mt.size() != 0) {
				for(int i =0; i<mt.size(); i++) {
					System.out.println(userSessionsMap.get(mt.get(i).getMemberId()));
					if(userSessionsMap.get(mt.get(i).getMemberId()) != null){				
						sessionmemberList.add(userSessionsMap.get(mt.get(i).getMemberId()));
					}	
				}
				
				if(sessionmemberList.size() != 0) {
					for (WebSocketSession sess : sessionmemberList) {
						sess.sendMessage(new TextMessage(so.getTeamName() + "팀에 게시물을 추가되었습니다."));
					}
				}
				
			}
			
		}
		
		else if(msg[0].equals("댓글 등록")) {
			if(msg[1] != null) {
						
				sessionmemberList.add(userSessionsMap.get(Integer.parseInt(msg[1])));
				
				if(sessionmemberList.size() != 0) {
					for (WebSocketSession sess : sessionmemberList) {
						sess.sendMessage(new TextMessage("작성하신 게시물에 댓글이 추가되었습니다."));
					}
				}
				
			}
			
		}
		
		
		
		else {
			
		for(int i = 0; i<msg.length; i++) {
			if("".equals(msg[i])) {

			}
			else {
				System.out.println(msg[i]);
				int intChk = Integer.parseInt(msg[i]);
				msgChk.add(intChk);
			}
		}

		for(int i = 0; i<msgChk.size(); i++) {

			System.out.println(msgChk.get(i));
		}

		for(int i =0; i<msgChk.size(); i++) {
			System.out.println(userSessionsMap.get(msgChk.get(i)));
			if(userSessionsMap.get(msgChk.get(i)) != null){				
				sessionmemberList.add(userSessionsMap.get(msgChk.get(i)));
			}	
		}

		if(sessionmemberList.size() != 0) {
			for (WebSocketSession sess : sessionmemberList) {
				sess.sendMessage(new TextMessage(login.getUsername() + "님이 알림을 추가하였습니다."));
			}
		}
		}
		
		
		
		

	}

//	// 클라이언트 연결을 끊었을 때 실행
//	@Override
//	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
//
//		sessionList.remove(session);
//
//		for (WebSocketSession sess : sessionList) {
//			sess.sendMessage(new TextMessage("connect"));
//		}
//
//	}


} 