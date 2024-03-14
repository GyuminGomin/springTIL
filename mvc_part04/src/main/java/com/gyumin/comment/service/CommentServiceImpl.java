package com.gyumin.comment.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gyumin.comment.mapper.CommentMapper;
import com.gyumin.comment.vo.CommentVO;
import com.gyumin.common.util.Criteria;
import com.gyumin.common.util.PageMaker;

import lombok.RequiredArgsConstructor;

/**
* TODO comment CommentService interface 구현객체
* @since 2024-03-13
* @implNote AJAX 방식으로 요청된 댓글 처리용 service interface 구현객체
*/
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{
	
	// CommentMapper 의존성 주입
	private final CommentMapper dao;

	@Override
	public List<CommentVO> commentList(int bno) throws Exception {
		return dao.commentList(bno);
	}

	private String getResult(int result) {
		return result == 1 ? "SUCCESS" : "FAILED";
	}
	
	@Override
	public String addComment(CommentVO vo) throws Exception {
		int result = dao.insert(vo);
		return getResult(result);
	}

	@Override
	public String updateComment(CommentVO vo) throws Exception {
		return getResult(dao.update(vo));
	}

	@Override
	public String deleteComment(int cno) throws Exception {
		return getResult(dao.delete(cno));
	}

	@Override
	public List<CommentVO> commentListPage(int bno, Criteria cri) throws Exception {
		return dao.listPage(bno, cri);
	}

	@Override
	public PageMaker getPageMaker(int bno, Criteria cri) throws Exception {
		int totalCount = dao.totalCount(bno);
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setDisplayPageNum(5);
		pageMaker.setTotalCount(totalCount);
		return pageMaker;
	}

}










