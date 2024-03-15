package com.bitc.sec.util;

import java.util.Hashtable;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.bitc.sec.security.user.CustomUser;
import com.bitc.sec.vo.ValidationMemberVO;

public class ChatHandler extends TextWebSocketHandler {

	// 사용자id/session
	private Map<String, WebSocketSession> sessionMap = new Hashtable<>();

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.out.println("연결됨 : " + session.getId());
		String userName = session.getPrincipal().getName();
		System.out.println("userName : " + userName);
		Authentication auth = (Authentication) session.getPrincipal();
		CustomUser customUser = (CustomUser) auth.getPrincipal();
		ValidationMemberVO vo = customUser.getMember();
		System.out.println(vo);
		sessionMap.put(userName, session);

	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		// client 에서 전달된 문자열 메세지 정보
		String msg = message.getPayload(); // 실제 전달한 메세지
		System.out.println(msg);
		for (WebSocketSession s : sessionMap.values()) {
			s.sendMessage(new TextMessage(msg));
		}
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		System.out.println("연결끊김 : " + session.getId());
		String userName = session.getPrincipal().getName();
		sessionMap.remove(userName);
	}

}
