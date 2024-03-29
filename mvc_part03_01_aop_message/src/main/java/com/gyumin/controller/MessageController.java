package com.gyumin.controller;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gyumin.service.MessageService;
import com.gyumin.vo.MessageVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/messages")
@Slf4j
@RequiredArgsConstructor
public class MessageController {
	
	private final MessageService ms;
	
	@GetMapping("/list")
	public ResponseEntity<List<MessageVO>> readList() {
		try {
			List<MessageVO> list = ms.list();
			return new ResponseEntity<>(list, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // 400 잘못된 요청
		}
	}
	
	@PostMapping("/add")
	public ResponseEntity<String> addMessage(MessageVO vo) {
		ResponseEntity<String> entity = null;
		
		try {
			log.info("MessageController : {}", vo);
			ms.addMessage(vo);
			log.info("MessageController service 작업 수행 완료");
			entity = new ResponseEntity<>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			HttpHeaders header = new HttpHeaders();
			header.add("Content-Type", "text/plain;charset=utf-8");
			entity = new ResponseEntity<>(e.getMessage(), header, HttpStatus.OK);
		}
		return entity;
	}
	
	@PatchMapping("/read/{mno}/{uid}")
	public ResponseEntity<Object> readMessage(
				@PathVariable(name="mno") int mno,
				@PathVariable(name="uid") String uid
			){
		ResponseEntity<Object> entity = null;
		
		try {
			MessageVO vo = ms.readMessage(uid, mno);
			entity = new ResponseEntity<>(vo, HttpStatus.OK);
		} catch (Exception e) {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON); // 보낼 때 JSON 형태로 보낼 수 있도록 설정
			entity = new ResponseEntity<>(e.getMessage(), headers, HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
}
