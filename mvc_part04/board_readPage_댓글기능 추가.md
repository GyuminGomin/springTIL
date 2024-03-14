### 게시물 상세보기 시 댓글 기능 추가

* * *
* 댓글을 저장할 table 생성 - common.sql 파일 참고

```sql
-- 2024-03-13 re_tbl_board 댓글 테이블 추가
CREATE TABLE IF NOT EXISTS re_tbl_comment(
	cno INT PRIMARY KEY auto_increment, 			-- 댓글 번호
	bno INT NOT NULL,						-- 댓글 작성 게시글 번호
	commentText TEXT NOT NULL,					-- 댓글 내용
	commentAuth VARCHAR(300) NOT NULL,				-- 작성자
	regdate TIMESTAMP NOT NULL DEFAULT now(),			-- 작성시간
	updatedate TIMESTAMP NOT NULL DEFAULT now(), 		-- 수정시간
	CONSTRAINT fk_re_tbl_bno FOREIGN KEY(bno)       		-- 외래키 지정
	REFERENCES re_tbl_board(bno) ON DELETE CASCADE, 		-- 게시글 삭제 시 참조하는 댓글도 삭제
	INDEX(bno)							-- bno column 인덱스 지정
);
```

* /WEB-INF/views/comment/comment.jsp 추가 : 댓글 기능 추가 <br/><br/>
* /WEB-INF/views/board/readPage.jsp 수정 : /WEB-INF/views/comment/home.jsp include 추가 <br/><br/>
* com.bitc.comment package class 추가 - controller, service, mapper, vo <br/><br/>

## Window -> ShowView -> other -> General -> tasks 에서 TODO 목록에서 comment 확인









