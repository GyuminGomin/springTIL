package com.bitc.board.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bitc.board.dao.BoardDAO;
import com.bitc.board.util.Criteria;
import com.bitc.board.util.PageMaker;
import com.bitc.board.vo.BoardVO;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

	private final BoardDAO dao;
	
	@Override
	public String regist(BoardVO board) throws Exception {
		String message = "등록 실패";
		int result = dao.create(board);
		if (result == 1) {
			// 등록 성공
			message = "등록 성공";
		}
		return message;
	}

	@Override
	public void updateCnt(int bno) throws Exception {
		dao.updateCnt(bno);
	}

	@Override
	public BoardVO read(int bno) throws Exception {
		BoardVO board = dao.read(bno);
		return board;
	}

	@Override
	public String modify(BoardVO board) throws Exception {
		String message = "수정 실패";
		int result = dao.update(board);
		if (result == 1) {
			message = "수정 성공";
		}
		return message;
	}

	@Override
	public String remove(int bno) throws Exception {
		String message = "삭제 실패";
		int result = dao.delete(bno);
		if (result == 1) {
			message = "삭제 성공";
		}
		return message;
	}

	@Override
	public List<BoardVO> listCriteria(Criteria cri) throws Exception {
		List<BoardVO> list = dao.listCriteria(cri);
		return list;
	}

	@Override
	public PageMaker getPageMaker(Criteria cri) throws Exception {
		PageMaker pm = new PageMaker(cri, dao.totalCount());
		return pm;
	}

}
