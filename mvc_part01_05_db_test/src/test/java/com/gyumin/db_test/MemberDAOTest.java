package com.gyumin.db_test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
	@Qualifier("md")
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
	
	// @Test
	public void readMember() {
		MemberVO member = md.readMember("id001");
		System.out.println("readMember : " + member);
	}
	
	// @Test
	public void readMemberWithPass() {
		String id = "id001";
		String pw = "pw001";
		MemberVO member = md.readMemberWithPass(id, pw);
		System.out.println("test member : " + member);
	}
	
	@Test
	public void readMemberList() {
		List<MemberVO> memberList = md.readMemberList();
		System.out.println("test memberList : " + memberList);
	}
	
	@Test
	public void updateMember() {
		MemberVO member = new MemberVO();
		member.setUno(1);
		member.setUserpw("12345");
		
		int result = md.updateMember(member);
		System.out.println("update result : " + result);
	}
	
	@Test
	public void deleteMember() {
		int result = md.removeMember(1);
		System.out.println("delete test : " + result);
	}
}
