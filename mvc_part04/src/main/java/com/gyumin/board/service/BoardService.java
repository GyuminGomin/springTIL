package com.gyumin.board.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gyumin.board.mapper.BoardMapper;
import com.gyumin.board.vo.BoardVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
	
	private final BoardMapper mapper;
	
	/**
	 * 원본글 등록 처리
	 * @param vo - 원본 글 정보
	 */
	@Transactional
	public void regist(BoardVO vo) throws Exception {
		// 원본글 등록
		mapper.register(vo);
		// 등록된 원본글 번호로 origin 컬럼 번호 수정
		mapper.updateOrigin();
	}

	/**
	 * 전체 원본글 목록
	 */
	public List<BoardVO> list() throws Exception{
		return mapper.list();
	}
}
