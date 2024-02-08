package com.bitc.board.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.bitc.board.service.BoardService;
import com.bitc.board.util.Criteria;
import com.bitc.board.util.PageMaker;
import com.bitc.board.vo.BoardVO;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BoardController {
	
	private final BoardService bs;
	
	/* "게시글 작성 페이지 요청" */
	// @RequestMapping(value="/register",method=RequestMethod.GET)
	@GetMapping("/board/register")
	public void register()throws Exception{
		// /board/register
		// WEB-INF/views/board/register.jsp
		System.out.println("게시글 작성 페이지 요청");
	}
	
	/**
	 * 게시글 등록 요청 처리 추가
	 */
	@PostMapping("board/register")
	public String create(HttpSession session, BoardVO board) {
		String path = "/board/register";
		String message = null;
		// dispatcher servlet이 board 객체로 바로 받아서 저장해줌
		try {
			// 등록 성공시 알림 등록
			message = bs.regist(board);
			path = "redirect:/";
		} catch (Exception e) {
			message = "등록 실패 : " + e.getMessage();
			e.printStackTrace();
		}
		session.setAttribute("msg", message);
		return path;
	}
	
	
	/**
	 * 게시글 상세보기 요청 처리 - 게시글 번호
	 */
	 @GetMapping("/board/readPage")
	 public String read(int bno, HttpServletRequest request, HttpSession session) {
		 // 상세보기 요청이 들어오면
		 String path = null;
		 BoardVO board = null;
		 try {
			board = bs.read(bno);
			if(board != null) {
				List<Integer> readList = (List<Integer>)session.getAttribute("readList");
				if(readList == null) {
					readList = new ArrayList<>();
					session.setAttribute("readList", readList);
				} // 먼저 존재하지 않으면 생성해주고
				// 처음 조회라면, 1카운트 올려주기
				if(readList != null && !readList.contains(bno)) {
					bs.updateCnt(bno);
					readList.add(bno);
				}
			}
			request.setAttribute("boardVO", board);
			path = "/board/read";
		} catch (Exception e) {
			// 만약 존재하지 않는다면 404page로 돌려주어야 함 (찾을 수 없는 페이지라고)
			e.printStackTrace();
		}
		return path;
	 }

	/**
	 * 게시글 수정 페이지 요청
	 * - 게시글 수정 화면 출력 
	 */
	 @GetMapping("board/modify")
	 public void modifyGet(int bno, HttpServletRequest request) {
		 // 수정 페이지 요청이 들어오면
		BoardVO board = null;
		try {
			board = (BoardVO)bs.read(bno);
		} catch (Exception e) {
			// 만약 찾을 수 없는 페이지를 요청했다면, 404 에러 페이지를 보여줘야 함
			e.printStackTrace();
		}
		request.setAttribute("boardVO", board);
	 }

	/**
	 * 게시글 수정 처리 요청
	 * 게시글 수정 완료 후 redirect - 수정게시글 상세보기 페이지 이동
	 */
	 @PostMapping("board/modify")
	 public String modifyPost(BoardVO board, HttpSession session) {
		 // 수정 요청이 들어오면,
		 String path = "redirect:/board/read"; // 수정 실패시
		 String message = null;
		 
		 try {
			// 수정 성공 시
			message = bs.modify(board);
			path = "redirect:/board/listPage";
		} catch (Exception e) {
			// 수정 실패 시
			message = "수정 실패" + e.getMessage();
			e.printStackTrace();
		}
		 session.setAttribute("msg", message);
		 return path;
	 }

	/**
	 * 게시글 삭제 완료 후 listPage 페이지 로 이동 - redirect 
	 */
	  @GetMapping("board/remove")
	  public String delete(int bno, HttpSession session) { 
		  // 삭제 요청이 들어오면
		  String path = "redirect:/board/read"; // 삭제 실패시
		  String message = null;
		  
		  try {
			message = bs.remove(bno);
			path = "redirect:/board/listPage";
		} catch (Exception e) {
			message = "삭제 실패" + e.getMessage();
			e.printStackTrace();
		}
		  session.setAttribute("msg", message);
		  return path;
	  }
	
	/**
	 *  페이징 처리 된 게시글 출력 페이지
	 *  param : 요청 page, perPageNum 
	 */
	// board/listPage
	 @GetMapping("/board/listPage")
	 public void listPage(Criteria cri, HttpServletRequest request) {
		// WEB-INF/views/board/listPage.jsp
		// 페이지 요청이 들어오면
		List<BoardVO> list = null;
		PageMaker pm = null;
		try {
			list = bs.listCriteria(cri);
			pm = bs.getPageMaker(cri);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// session에 페이지 등록
		request.setAttribute("list", list);
		request.setAttribute("pm", pm);
		System.out.println("게시글 목록 페이지 요청");
	}

	
}















