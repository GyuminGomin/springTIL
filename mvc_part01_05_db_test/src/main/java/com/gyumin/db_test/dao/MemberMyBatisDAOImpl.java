package com.gyumin.db_test.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.gyumin.db_test.vo.MemberVO;

import lombok.RequiredArgsConstructor;

@Repository("md")
@RequiredArgsConstructor
public class MemberMyBatisDAOImpl implements MemberDAO {

	private final SqlSession session;
	
	@Override
	public int insertMember(MemberVO member) {
		int result = session.insert("MemberMapper.insertMember", member);
		System.out.println("MemberDAO insert result : " + result);
		return result;
	}

	@Override
	public MemberVO readMember(String userid) {
		MemberVO member = session.selectOne("MemberMapper.read", userid);
		System.out.println("MemberDAO read result : " + member);
		return member;
	}

	@Override
	public MemberVO readMemberWithPass(String userid, String userpw) {
		Map<String, String> map = new HashMap<>();
		map.put("id", userid);
		map.put("pw", userpw);
		MemberVO member = session.selectOne("MemberMapper.readWithPass", map);
		// 객체로도 가져올 수 있지만, 공간 낭비가 될 수 있으므로 Map을 사용
		/*
		MemberVO m = new MemberVO();
		m.setUserid(userid);
		m.setUserpw(userpw);
		MemberVO member = session.selectOne("MemberMapper.readPass", m);
		*/
		System.out.println("MemberDAO readWithPass result : " + member);
		return member;
	}

	@Override
	public List<MemberVO> readMemberList() {
		List<MemberVO> list = session.selectList("MemberMapper.memberList"); 
		return list;
	}

	@Override
	public int updateMember(MemberVO member) {
		int result = session.update("MemberMapper.updateMember", member);
		return result;
	}

	@Override
	public int removeMember(int uno) {
		int result = session.delete("MemberMapper.remove", uno);
		return result;
	}

}
