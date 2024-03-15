package com.bitc.sec.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.bitc.sec.security.user.CustomUser;
import com.bitc.sec.service.MemberService;
import com.bitc.sec.vo.ValidationMemberVO;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {

	@Autowired
	private MemberService ms;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth)
			throws IOException, ServletException {
		System.out.println(auth);
		System.out.println(auth.getPrincipal());
		CustomUser user = (CustomUser) auth.getPrincipal();
		System.out.println(user);
		ValidationMemberVO vo = user.getMember();
		System.out.println(vo);

		try {
			// 로그인이 성공한 사용자의
			// 최종 방문시간 업데이트
			ms.updateVisteDate(vo.getU_id());
		} catch (Exception e) {
			e.printStackTrace();
		}

		String context = request.getContextPath();
		response.sendRedirect(context);
		System.out.println("redirect : " + context);

	}

}
