package com.gyumin.db_test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gyumin.db_test.dao.MemberDAO;
import com.gyumin.db_test.vo.MemberVO;

@RunWith(SpringJUnit4ClassRunner.class) // 어떤 걸로 진행할 것인지 클래스 설정
@ContextConfiguration(
	locations = {"classpath:context/root-context.xml"}
)
public class MemberDAOTest {

	@Autowired
	MemberDAO md;
	
	// @Test
	public void testInsertMember() {
		MemberVO member = new MemberVO();
		member.setUserid("id001");
		member.setUserpw("pw001");
		member.setUsername("IRONMAN");
		
		int result = md.insertMember(member);
		System.out.println("insertMember result : " + result);
	}
	
	@Test
	public void readMember() {
		MemberVO member = md.readMember("id001");
		System.out.println("readMember : " + member);
	}
}
