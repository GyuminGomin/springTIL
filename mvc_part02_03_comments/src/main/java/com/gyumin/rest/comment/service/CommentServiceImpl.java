package com.gyumin.rest.comment.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.bitc.board.util.Criteria;
import com.gyumin.rest.comment.model.CommentVO;
import com.gyumin.rest.comment.repository.CommentMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

	private final CommentMapper mapper;
	
	@Override
	public List<CommentVO> commentList(int bno) throws Exception {
		return mapper.commentList(bno);
	}

	@Override
	public String addComment(CommentVO vo) throws Exception {
		int result = mapper.insert(vo);
		String message = result == 0 ? "요청 처리 실패" : "요청 처리 성공"; 
		return message;
	}

	@Override
	public String updateComment(CommentVO vo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteComment(int cno) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> commentListPage(int bno, Criteria cri) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
