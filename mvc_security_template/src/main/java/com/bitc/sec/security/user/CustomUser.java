package com.bitc.sec.security.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.bitc.sec.vo.ValidationMemberVO;

import lombok.Getter;

/**
 * 
 * 인가가 완료된 사용자 정보
 */
public class CustomUser extends User {

	private static final long serialVersionUID = 1L;

	@Getter
	private ValidationMemberVO member;

	public CustomUser(ValidationMemberVO member) {
		super(member.getU_id(), member.getU_pw(), authorities(member.getAuthList()));
		this.member = member;
	}

	public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}

	public static Collection<? extends GrantedAuthority> authorities(List<String> authList) {
		List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
		for (String auth : authList) {
			grantedAuthorityList.add(new SimpleGrantedAuthority(auth));
		}
		return grantedAuthorityList;
	}

}
