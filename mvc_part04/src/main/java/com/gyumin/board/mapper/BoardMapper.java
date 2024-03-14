package com.gyumin.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import com.gyumin.board.provider.BoardQueryProvider;
import com.gyumin.board.vo.BoardVO;
import com.gyumin.common.util.SearchCriteria;

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
	 * origin으로 역순 정렬 후 같은 origin 값이면 seq 오름차순으로 정렬
	 */
	@Select("SELECT * FROM re_tbl_board ORDER BY origin DESC, seq ASC")
	List<BoardVO> list() throws Exception;

	/**
	 * 매개변수로 전달받은 게시글 번호와 일치하는 게시글 정보 반환
	 */
	@Select("SELECT * FROM re_tbl_board WHERE bno = #{bno}")
	BoardVO readBoard(int bno) throws Exception;

	@Update("UPDATE re_tbl_board SET viewcnt = viewcnt + 1 WHERE bno = #{bno}")
	void updateViewCnt(int bno) throws Exception;

	@Update("UPDATE re_tbl_board SET seq = seq + 1 WHERE origin = #{origin} AND seq > #{seq}")
	void updateReply(BoardVO reply) throws Exception;

	@Insert("INSERT INTO re_tbl_board(title,content,writer,origin,depth,seq,uno) VALUES(#{title}, #{content}, #{writer}, #{origin}, #{depth}, #{seq}, #{uno})")
	void replyRegister(BoardVO reply) throws Exception;

	@Update("UPDATE re_tbl_board SET title = #{title}, content = #{content}, writer = #{writer}, updatedate = now() WHERE bno = #{bno}")
	void modify(BoardVO board) throws Exception;

	@Update("UPDATE re_tbl_board SET showboard = 'n', updatedate = now() WHERE bno = #{bno}")
	void remove(int bno) throws Exception;
	
	/**
	 * TODO board search BoardMapper check
	 * @since 2024-03-13
	 * @param 검색 기능이 추가된 SearchCriteria
	 * @return - 검색된 조건 내의 게시글 목록
	 * @see {@link com.bitc.board.provider.BoardQueryProvider} <br/>
	 * {@summary} BoardQueryProvider class의 searchSelectQuery method를 호출 하여
	 * 반환된 결과로 쿼리 작업 수행
	 */
	@SelectProvider(type=BoardQueryProvider.class, method="searchSelectQuery")
	List<BoardVO> listCriteria(SearchCriteria cri)throws Exception;

	
	/**
	 * TODO board search BoardMapper check
	 * @since 2024-03-13
	 * @param cri 전체 게시글 개수를 검색하기위한 페이지 ,한번에 보여줄 게시글 개수, 검색타입, 키워드
	 * @return - 검색 범위내의 전체 게시글 개수
	 */
	@SelectProvider(type=BoardQueryProvider.class, method="searchSelectCount")
	int listCountCriteria(SearchCriteria cri) throws Exception;
}
