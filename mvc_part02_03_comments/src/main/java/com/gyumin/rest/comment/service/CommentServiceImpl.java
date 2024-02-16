package com.gyumin.rest.comment.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.bitc.board.util.Criteria;
import com.bitc.board.util.PageMaker;
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

	private String getResult(int result) {
		return result == 0 ? "요청 처리 실패" : "요청 처리 성공"; 
	}
	
	@Override
	public String addComment(CommentVO vo) throws Exception {
		int result = mapper.insert(vo);
		return getResult(result);
	}

	@Override
	public String updateComment(CommentVO vo) throws Exception {
		int result = mapper.update(vo);
		return getResult(result);
	}

	@Override
	public String deleteComment(int cno) throws Exception {
		return getResult(mapper.delete(cno));
	}

	@Override
	public Map<String, Object> commentListPage(int bno, Criteria cri) throws Exception {
		Map<String, Object> map = new HashMap<>();
		List<CommentVO> list = mapper.listPage(bno, cri);
		map.put("list", list);
		
		PageMaker pm = new PageMaker();
		pm.setCri(cri);
		
		int totalCount = mapper.totalCount(bno);
		pm.setTotalCount(totalCount);
		map.put("pm", pm);
		return map;
	}

}
