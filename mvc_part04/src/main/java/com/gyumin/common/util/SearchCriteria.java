package com.gyumin.common.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * TODO board search SearchCriteria check
 * @since 2024-03-13
 * @implNote - Criteria 객체에서 검색 기능 정보를 추가 계산및 저장
 */
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor()
/**
 * toString 부모 toString도 추가
 */
@ToString(callSuper = true) 
public class SearchCriteria extends Criteria{
	
	/**
	 * 검색할 column
	 */
	private String searchType;
	/**
	 * 검색할 키워드 
	 */
	private String keyword;
	
}




