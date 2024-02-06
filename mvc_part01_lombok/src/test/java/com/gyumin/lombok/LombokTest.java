package com.gyumin.lombok;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LombokTest {
	
	@Before
	public void before() {
		System.out.println("test method 실행 전 처리되는 메소드");
	}
	
	@Test // 이 메소드가 junit이 테스트 하기 위한 기능
	public void lombokTest() {
		log.info("lombok library test");
		/*
		UserVO user = new UserVO("id001", "pw001", "김규민");
//		user.setUid("id001");
//		user.setUpw("pw001");
//		user.setUname("김규민");
		user.setUno(1);
		user.setRegdate(new Date());
		log.info("test user info : {} {}", user, "입니다.");
		
		UserVO user2 = new UserVO("id001", "pw001", "원빈");
		*/
		List<String> list = new ArrayList<>();
		list.add("이순신");
		list.add("세종대왕");
		list.add("원호대사");
		list.add("이이");
		list.add("이황");
		list.add("권율");
		list.add("궁예");
		list.add("왕권");
		UserVO user = UserVO.builder()
							.uno(1)
							.uid("id001")
							.upw("pw001")
							.uname("김규민")
							.regdate(new Date())
							.friendList(list)
							.build();
		
		UserVO user2 = UserVO.builder()
				.uno(2)
				.uid("id002")
				.upw("pw002")
				.uname("홍길동")
				.list("김유신")
				.list("조조")
				.regdate(new Date())
				.build();
		log.info("user : {} ", user);
		log.info("user2 : {} ", user2);
		log.info("user1 equals user2 - {}", user.equals(user2));
		
	}
	@After
	public void after() {
		log.warn("test method 실행 후 처리되는 메소드");
	}
}
