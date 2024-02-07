package com.gyumin.db_test.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import com.gyumin.db_test.vo.MemberVO;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MemberDAOImpl implements MemberDAO {
	// 필수 인자 생성을 통해 의존성 주입 - 객체 생성 이후 필드 변경 불가
	private final DataSource ds;
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	@Override
	public int insertMember(MemberVO member) {
		int result = 0;
		try {
			conn = ds.getConnection();
			String sql = "INSERT INTO tbl_member(userid,userpw,username) VALUES(?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getUserid());
			pstmt.setString(2, member.getUserpw());
			pstmt.setString(3, member.getUsername());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
			} catch (SQLException e) {}
			try {
				if(conn != null) conn.close();
			} catch (SQLException e) {}
		}
		
		return result;
	}

	@Override
	public MemberVO readMember(String userid) {
		MemberVO member = null;
		String sql = "SELECT * FROM tbl_member WHERE userid = ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				member = new MemberVO();
				member.setUno(rs.getInt(1));
				member.setUserid(rs.getString(2));
				member.setUserpw(rs.getString(3));
				member.setUsername(rs.getString(4));
				member.setRegdate(rs.getTimestamp(5));
				member.setUpdatedate(rs.getTimestamp(6));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
			} catch (SQLException e) {}
			try {
				if(pstmt != null) pstmt.close();
			} catch (SQLException e) {}
			try {
				if(conn != null) conn.close();
			} catch (SQLException e) {}
		}
		return member;
	}

	@Override
	public MemberVO readMemberWithPass(String userid, String userpw) {
		return null;
	}

	@Override
	public List<MemberVO> readMemberList() {
		return null;
	}

	@Override
	public int updateMember(MemberVO member) {
		return 0;
	}

	@Override
	public int removeMember(int uno) {
		// TODO Auto-generated method stub
		return 0;
	}

}
