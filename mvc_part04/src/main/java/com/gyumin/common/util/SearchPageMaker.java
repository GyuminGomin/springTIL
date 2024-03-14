package com.gyumin.common.util;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * TODO board search SearchPageMaker check
 * @since 2024-03-13
 * @apiNote 검색 기능이 추가된 페이징 처리 정보를 생성하는 객체 추가
 */
public class SearchPageMaker extends PageMaker{

	/**
	 * GET 방식의 QueryString 생성을
	 * 검색 기능이 추가된 형식으로 추가
	 */
	@Override
	public String makeQuery(int page) {
		SearchCriteria sCri = (SearchCriteria)cri;
		// QueryString 생성 및 조합 객체
		UriComponents uriComponents 
			= UriComponentsBuilder.newInstance().queryParam("page", page)
			  .queryParam("perPageNum", cri.getPerPageNum())
			  .queryParam("searchType", sCri.getSearchType())
			  .queryParam("keyword", sCri.getKeyword())
			  .build();
		String query = uriComponents.toString();	  
		return query;
	}
	
	

}










