package com.gyumin.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gyumin.mapper.MessageMapper;
import com.gyumin.mapper.UserMapper;
import com.gyumin.vo.MessageVO;
import com.gyumin.vo.UserVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

	private final MessageMapper mapper;
	private final UserMapper userMapper;
	
	@Transactional
	@Override
	public void addMessage(MessageVO vo) throws Exception {
		log.info("addMessage Service 시작");
		// 발신자 포인트 증가 + 10
		UserVO uv = new UserVO();
		uv.setUid(vo.getSender());
		uv.setUpoint(10);
		userMapper.updatePoint(uv);
		// 메시지 등록
		mapper.create(vo);
		log.info("addMessage Service 종료");
	}

	@Override
	public List<MessageVO> list() throws Exception {
		log.info("MessageService list 호출-------------------");
		return mapper.list();
	}

	@Transactional
	@Override
	public MessageVO readMessage(String uid, int mno) throws Exception {
		
		/*
		// 메시지 번호로 하나의 메시지 정보 읽기
		MessageVO message = mapper.readMessage(mno);
		if (message.getOpendate() != null) {
			// 이미 수신한 메시지
			throw new NullPointerException("이미 읽은 메시지 입니다.");
		}
		*/
		
		// opendate == now()
		mapper.updateMessage(mno);
		
		// 수신자 포인트 증가 +5
		UserVO vo = new UserVO();
		vo.setUid(uid);
		vo.setUpoint(5);
		userMapper.updatePoint(vo);
		
		// opendate 수정 완료된 메시지 정보 반환
		return mapper.readMessage(mno);
	}
}
