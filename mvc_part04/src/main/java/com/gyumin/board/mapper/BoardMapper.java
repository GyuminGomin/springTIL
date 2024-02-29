package com.gyumin.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.gyumin.board.vo.BoardVO;

/**
 * 질문과 답변 게시판
 * re_tbl_board table 요청 처리
 */
public interface BoardMapper {
	
	@Insert("INSERT INTO re_tbl_board(title, content, writer, uno) VALUES(#{title}, #{content}, #{writer}, #{uno})")
	void register(BoardVO board) throws Exception;
	
	@Update("UPDATE re_tbl_board SET origin = LAST_INSERT_ID() WHERE bno = LAST_INSERT_ID()")
	void updateOrigin() throws Exception;

	/**
	 * 전체 원본글 게시글 목록을 검색
	 */
	@Select("SELECT * FROM re_tbl_board ORDER BY origin DESC")
	List<BoardVO> list() throws Exception;
}
