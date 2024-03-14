package com.gyumin.board.provider;

import org.apache.ibatis.jdbc.SQL;

import com.gyumin.common.util.SearchCriteria;


/**
 * TODO board search BoardQueryProvider check
 * @since 2024-03-13
 * 조건또는 분기가 필요한 복잡한 쿼리 생성 시 동적으로 쿼리를 생성을 도와주는
 * Query Provider(쿼리 생성 제공 class)
 * @apiNote 
 * {@link com.bitc.board.mapper.BoardMapper.listCriteria}
 */
public class BoardQueryProvider {

	/**
	 * @since 2024-03-13
	 * @param mapper method의 매개변수를 전달
	 * @return - 쿼리 생성 후 실행할 DB쿼리를 문자열로 반환
	 */
	public String searchSelectQuery(SearchCriteria cri) {
		// SQL : mybatis 에서 동적 쿼리 생성을 지원하는 객체
		SQL sql = new SQL();
		// 검색할 column
		sql.SELECT("bno, title, writer, regdate, showboard, viewcnt");
		// 검색할 table
		sql.FROM("re_tbl_board");
		
		// 검색 조건을 추가하는건 검색 범위내 전체 게시글 개수로 페이징 처리에서도 사용되므로
		// 따로 메소드 생성
		searchCondition(cri, sql);
		
		// 정렬 조건
		sql.ORDER_BY("origin DESC, seq ASC");
		// SELECT 범위
		sql.LIMIT(cri.getStartRow()+", "+cri.getPerPageNum());
		System.out.println("BoardQueryProcider searchSelectQuery : "+ sql.toString());
		return sql.toString();
	}
	
	/**
	 * @since 2024-03-13
	 * @param SearchCriteria 검색된 전체 게시글 개수를 선별할 정보
	 * @return 검새된 범위 내의 전체 게시물 개수
	 */
	public String searchSelectCount(SearchCriteria cri) {
		SQL sql = new SQL();
		sql.SELECT("count(*)"); 
		sql.FROM("re_tbl_board");
		searchCondition(cri, sql);
		return sql.toString();
	}
	
	/**
	 * t == title에서 검색, c == content에서 검색, w == writer 에서 검색
	 * tc == title||content 검색, cw == content||writer 검색, tcw == title||content||writer 검색 
	 * searchType의 각 키워드에 따라서 table의 해당 column을 검색
	 * 매개변수로 전달 받은 SQL 객체에 검색 조건 쿼리문 생성 추가
	 */
	private void searchCondition(SearchCriteria cri, SQL sql) {
		
		String titleWhere = "title LIKE CONCAT('%','"+cri.getKeyword()+"','%')";
		String contentWhere = "content LIKE CONCAT('%','"+cri.getKeyword()+"','%')";
		String writerWhere = "writer LIKE CONCAT('%','"+cri.getKeyword()+"','%')";
		
		String type = cri.getSearchType();
		// 검색 타입이 존재한다면 - 사용자가 검색 요청을 하였을 시
		if(type != null && !type.trim().equals("") && !type.trim().equals("n")) {
			// t, c, tc, cw, tcw
			// OR() 는 WHERE 이 있을 시 OR 절 추가 없으면 WHERE 절 추가
			if(type.contains("t")) {
				sql.OR().WHERE(titleWhere);
			}
			if(type.contains("c")) {
				sql.OR().WHERE(contentWhere);
			}
			if(type.contains("w")) {
				sql.OR().WHERE(writerWhere);
			}
		}
	}
}



















