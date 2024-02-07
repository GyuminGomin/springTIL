package com.gyumin.db_test;

import java.sql.Connection;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gyumin.db_test.vo.MemberVO;

@RunWith(SpringJUnit4ClassRunner.class) // 어떤 걸로 진행할 것인지 클래스 설정
@ContextConfiguration(
	locations = {"classpath:context/root-context.xml"}
)
public class DataSourceTest {

	@Autowired
	DataSource ds;
	
	@Autowired
	SqlSessionFactory sql;
	
	@Test
	public void read() {
		SqlSession session = sql.openSession();
		MemberVO member = session.selectOne("MemberMapper.read", "id002");
		System.out.println(member);
	}
	
	// @Test
	public void sqlSessionFactoryTest() {
		SqlSession session = sql.openSession(); // 생성자를 이미 bean에 넣어줬기 때문에 기본생성자로 사용 가능
		System.out.println("SqlSession" + session);
		MemberVO member = new MemberVO();
		member.setUserid("id002");
		member.setUserpw("pw002");
		member.setUsername("THOR");
		
		int result = session.insert("MemberMapper.insertMember", member);
		System.out.println("session insert : " + result);
	}
	
	@Test
	public void connectionPoolTest() throws Exception{
		Connection conn = ds.getConnection();
		System.out.println("test connection : " + conn);
		conn.close();
	}
	
}
