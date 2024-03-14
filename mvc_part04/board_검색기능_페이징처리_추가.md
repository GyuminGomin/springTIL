### 게시물 검색 기능 및 페이징 처리 추가

* * *

* /WEB-INF/views/board/listReply.jsp 수정 : 검색 기능 뷰 추가 - 페이징 블럭 추가 <br/><br/>
* com.bitc.common.util package class 추가

> Criteria에 검색 정보도 추가하기 위한 확장 클래스 SearchCriteria 추가 <br/>
> PageMaker에 검색 범위내의 페이징 처리를 위한 SearchPageMaker 추가 <br/>

* com.bitc.board.controller.listReply method 매개변수 추가 - SearchCriteria 
* com.bitc.board.service.BoardService method 추가 - listCriteria(SearchCriteria cri)
* com.bitc.board.mapper.BoardMapper method 추가 

> com.bitc.board.mapper.BoardMapper.listCriteria(SearchCriteria cri); <br/>
> com.bitc.board.mapper.BoardMapper.listCountCriteria(SearchCriteria cri);

* 조건에 따라 쿼리가 변경되어야 함으로 - searchType에 따른 WHERE 조건절 변화
* 동적 쿼리작업을 도와줄 쿼리제공자(QueryProvider 추가)

> com.bitc.board.provider.BoardQueryProvider

## Window -> ShowView -> other -> General -> tasks 에서 TODO 목록에서 board search 확인









