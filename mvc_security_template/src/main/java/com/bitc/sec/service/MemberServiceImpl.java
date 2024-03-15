package com.bitc.sec.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bitc.sec.mapper.MemberMapper;
import com.bitc.sec.vo.AuthDTO;
import com.bitc.sec.vo.ValidationMemberVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

	private final MemberMapper dao;
	private final PasswordEncoder encoder;

	@Transactional
	@Override
	public void memberJoin(ValidationMemberVO vo) throws Exception {
		String u_pw = vo.getU_pw();
		System.out.println("암호화 전 : " + u_pw);
		vo.setU_pw(encoder.encode(u_pw));
		System.out.println("암호화 후 : " + vo.getU_pw());
		dao.memberJoin(vo);
		// 일반회원 권한 부여
		dao.insertAuth(vo.getU_id());
	}

	@Override
	public boolean getMemberByID(String u_id) throws Exception {
		return dao.getMemberByID(u_id) == null ? true : false;
	}

	@Override
	public void updateVisteDate(String u_id) throws Exception {
		dao.updateVistDate(u_id);
	}

	@Override
	public void deleteYN(ValidationMemberVO vo) throws Exception {
		dao.deleteYN(vo);
	}

	@Override
	public List<AuthDTO> updateAuth(AuthDTO vo) throws Exception {
		List<AuthDTO> list = dao.getAuthDTOList(vo.getU_id());
		boolean isNull = true;
		for (AuthDTO auth : list) {
			if (vo.getU_auth().equals(auth.getU_auth())) {
				// 해당 사용자의 권한 삭제
				dao.deleteAuth(auth);
				isNull = false;
				break;
			}
		}
		// 권한이 존재하지 않을때 권한 추가
		if (isNull)
			dao.insertMemberAuth(vo);

		// 변경 권한 리스트 반환
		return dao.getAuthDTOList(vo.getU_id());
	}

	@Override
	public List<ValidationMemberVO> getMemberList() throws Exception {
		List<ValidationMemberVO> vo = dao.getMemberList();
		for (ValidationMemberVO member : vo) {
			// 해당 사용자 보유 권한 목록 저장
			member.setAuthList(dao.getAuthList(member.getU_id()));
		}
		return vo;
	}

}
