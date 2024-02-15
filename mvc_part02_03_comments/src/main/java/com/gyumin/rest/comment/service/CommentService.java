package com.gyumin.rest.comment.service;

import java.util.List;
import java.util.Map;

import com.bitc.board.util.Criteria;
import com.gyumin.rest.comment.model.CommentVO;

public interface CommentService {
	
	/**
	 * @param bno
	 * @return - 게시글 번호를 전달 받아서 bno 값이 일치하는 댓글 리스트 반환
	 */
	List<CommentVO> commentList(int bno) throws Exception;
	
	/**
	 * @param vo
	 * @return - 댓글 삽입 처리 후 처리된 결과를 문자열로 반환
	 */
	String addComment(CommentVO vo) throws Exception;
	
	/**
	 * @param vo
	 * @return - 댓글 수정 처리 후 처리된 결과를 문자열로 반환
	 */
	String updateComment(CommentVO vo) throws Exception;
	
	/**
	 * @param cno
	 * @return - 댓글 삭제 후 처리된 결과를 문자열로 반환
	 */
	String deleteComment(int cno) throws Exception;
	
	/**
	 * 페이징 처리된 게시글 목록 요청 시 페이징 처리된 게시글 목록과
	 * 페이징 블럭 출력에 필요한 pageMaker 정보를 반환
	 */
	Map<String, Object> commentListPage(int bno, Criteria cri) throws Exception;
}
