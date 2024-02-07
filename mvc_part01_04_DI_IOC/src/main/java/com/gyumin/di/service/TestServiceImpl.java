package com.gyumin.di.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gyumin.di.dao.TestDAO;
import com.gyumin.di.dao.TestDAOImpl;
import com.gyumin.di.dao.TestDBCPDAOImpl;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Service("ts")
@Slf4j
public class TestServiceImpl implements TestService {

	@Setter(onMethod_= {@Autowired, @Qualifier("td")})
	private TestDAO dao;
	
	/*
	@Autowired
	@Qualifier("testDAOImpl")
	public void setDao(TestDAO dao) {
		this.dao = dao;
	}
	*/
	
	public TestServiceImpl() {
		log.info("testService Impl 생성");
	}
	
	@Override
	public void testService(String message) {
		log.info("test service message : {} ", message);
		dao = new TestDAOImpl();
		System.out.println("dao : " + dao);
	}

}
