package com.gyumin.user.service;

import org.springframework.stereotype.Service;

import com.gyumin.user.mapper.UserMapper;
import com.gyumin.user.vo.UserVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserMapper mapper;
	
	public void signUp(UserVO vo) throws Exception{
		mapper.signUp(vo);
	}
	
}
