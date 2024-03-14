package com.gyumin.comment.controller;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gyumin.comment.service.CommentService;
import com.gyumin.comment.vo.CommentVO;
import com.gyumin.common.util.Criteria;
import com.gyumin.common.util.PageMaker;

import lombok.RequiredArgsConstructor;

/**
 * TODO comment CommentController(Rest, ResponseBody) 
 * @since 2024-03-13
 * @apiNote AJAX 방식으로 요청된 댓글 처리용 controller
 */
@RestController 
// Controller에 명시된 모든 Mapping method가
// data를 전달하는 @ResponseBody 임을 명시
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {
	
	private final CommentService cs;
	
	
	@PostMapping("")
	public ResponseEntity<String> addComment(CommentVO vo) {
		System.out.println(vo);
		ResponseEntity<String> entity = null;
		// response header 설정 정보 추가 -- springframework headers
		HttpHeaders headers = new HttpHeaders();
		//   application/json
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setContentType(new MediaType("application","json",Charset.forName("utf-8")));
		try {
			String message = cs.addComment(vo);
			// 필수 값은 상태 코드
			entity = new ResponseEntity<>(message,headers,HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<>(
					e.getMessage(),
					headers,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return entity;
	}
	
	// comments/{cno}
	@PatchMapping("/{cno}")
	public String update(
			@PathVariable(name="cno") int cno,
			@RequestBody CommentVO vo
			) throws Exception{
		System.out.println(vo);
		vo.setCno(cno);
		System.out.println(vo);
		String result = cs.updateComment(vo);
		return result;
	}

	// comments/{cno}
	@DeleteMapping("/{cno}")
	public String delete(
			@PathVariable(name="cno") int cno
			) throws Exception{
		String result = cs.deleteComment(cno);
		return result;
	}
	
	// 페이징 처리된 게시글 목록
	// comments/bno/page
	@GetMapping("/{bno}/{page}")
	// 페이징 처리된 게시글 목록
	// 이동할 수 있는 페이지 번호의 정보를 저장하는 페이징 블럭
	// List<CommentVO>, PageMaker
	public Map<String,Object> listPage(
			@PathVariable(name="bno") int bno,
			@PathVariable(name="page") int page
			)throws Exception{
		Map<String, Object> map = new HashMap<>();
		Criteria cri = new Criteria(page,5);
		List<CommentVO> list = cs.commentListPage(bno,cri);
		PageMaker pm = cs.getPageMaker(bno, cri);
		map.put("pm", pm);
		map.put("list", list);
		return map;
	}
	
}





















