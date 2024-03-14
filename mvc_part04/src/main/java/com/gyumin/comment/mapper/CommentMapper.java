package com.gyumin.comment.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.gyumin.comment.vo.CommentVO;
import com.gyumin.common.util.Criteria;

/**
 * TODO comment CommentMapper
 * @since 2024--03-13
 * @apiNote MyBatis ORM Mapper - DataBase 요청 처리
 */
public interface CommentMapper {
	
	/**
	 * 전체 댓글 목록
	 * @param 댓글이 저장된 게시글 번호
	 * @return 해당 게시글의 전체 게시글 목록
	 */
	@Select("SELECT * FROM re_tbl_comment WHERE bno = #{bno} ORDER BY cno DESC")
	List<CommentVO> commentList(int bno)throws Exception;
	
	/**
	 * @param 삽입될 댓글 정보
	 * @return - 삽입된 행의 개수
	 */
	@Insert("INSERT INTO re_tbl_comment(bno,commentText,commentAuth) "
			+ "VALUES(#{bno},#{commentText},#{commentAuth})")
	int insert(CommentVO vo) throws Exception;
	
	/**
	 * @param  수정할 댓글 정보
	 * @return  수정된 행의 개수
	 */
	@Update("UPDATE re_tbl_comment SET "
			+ " commentAuth = #{commentAuth} , "
			+ " commentText = #{commentText} , "
			+ " updatedate = now() "
			+ " WHERE cno = #{cno}")
	int update(CommentVO vo) throws Exception;
	
	/**
	 * @param 삭제할 댓글 번호
	 * @return 삭제된 행의 개수
	 */
	@Delete("DELETE FROM re_tbl_comment WHERE cno = #{cno}")
	int delete(int cno) throws Exception;
	
	/**
	 * 페이징 처리 
	 */
	/**
	 * @param bno - 검색할 게시글 번호
	 * @param cri - 요청한 페이지에 따른 페이징 정보
	 * @return - 페이징 처리된 게시글 목록
	 */
	@Select("SELECT * FROM re_tbl_comment "
			+ " WHERE bno = #{bno} ORDER BY cno DESC "
			+ " limit #{cri.startRow}, #{cri.perPageNum}") 
	List<CommentVO> listPage(
			@Param("bno") int bno, 
			@Param("cri")Criteria cri) throws Exception;
	
	/**
	 * @param bno 검색할 게시글 번호
	 * @return 해당 게시글에 작성된 댓글 개수
	 */
	@Select("SELECT count(*) FROM re_tbl_comment WHERE bno = #{bno}")
	int totalCount(int bno) throws Exception;
	
}

















