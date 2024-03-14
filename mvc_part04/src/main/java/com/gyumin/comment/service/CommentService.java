package com.gyumin.comment.service;

import java.util.List;

import com.gyumin.comment.vo.CommentVO;
import com.gyumin.common.util.Criteria;
import com.gyumin.common.util.PageMaker;

/**
 * TODO comment CommentService interface 
 * @since 2024-03-13
 * @apiNote AJAX 방식으로 요청된 댓글 처리용 service interface
 */
public interface CommentService {
	/**
	 * 게시글 번호를 전달 받아서  
	 * bno 값이 일치하는 전체 댓글 리스트 
	 */
	List<CommentVO> commentList(int bno)throws Exception;
	
	/**
	 *  댓글 삽입
	 */
	String addComment(CommentVO vo) throws Exception;
	
	/**
	 * 댓글 수정
	 */
	String updateComment(CommentVO vo) throws Exception;
	
	/**
	 * 댓글 삭제
	 */
	String deleteComment(int cno)throws Exception;
	
	/**
	 * 페이징 처리된 댓글 리스트
	 */
	List<CommentVO> commentListPage(
			int bno, Criteria cri
			)throws Exception;
	
	/**
	 * 페이징 블럭 정보
	 */
	PageMaker getPageMaker(int bno, Criteria cri) throws Exception;
}











