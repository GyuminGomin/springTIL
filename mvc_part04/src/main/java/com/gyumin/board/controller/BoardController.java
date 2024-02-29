package com.gyumin.board.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gyumin.board.service.BoardService;
import com.gyumin.board.vo.BoardVO;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("board/*")
@RequiredArgsConstructor
public class BoardController {
	
	private final BoardService bs;
	
	@GetMapping("listReply")
	public String listReply(Model model) throws Exception {
		List<BoardVO> list = bs.list();
		model.addAttribute("qnaList", list);
		return "board/listReply";
	}
	
	// 게시글 등록 화면 요청
	// board/register
	@GetMapping("register")
	public String register() {
		return "board/register";
	}
	
	// 게시글 등록 처리 요청
	@PostMapping("register")
	public String register(BoardVO boardVO) throws Exception {
		bs.regist(boardVO);
		return "redirect:/board/listReply";
	}
}
