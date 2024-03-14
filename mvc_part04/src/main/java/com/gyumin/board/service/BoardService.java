package com.gyumin.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gyumin.board.mapper.AttachmentMapper;
import com.gyumin.board.mapper.BoardMapper;
import com.gyumin.board.vo.BoardVO;
import com.gyumin.common.util.SearchCriteria;
import com.gyumin.common.util.SearchPageMaker;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
	
	private final BoardMapper mapper;
	private final AttachmentMapper attachMapper;
	
	/**
	 * 원본글 등록 처리
	 * @param vo - 원본 글 정보
	 */
	@Transactional
	public void regist(BoardVO vo) throws Exception {
		// 원본글 등록
		mapper.register(vo);
		// 등록된 원본글 번호로 origin 컬럼 번호 수정
		mapper.updateOrigin();
		// 첨부된 파일 이름 리스트
		List<String> files = vo.getFiles();
		if(files != null && !files.isEmpty()) {
			for (String fullName : files) {
				attachMapper.addAttach(fullName);
			}
		}
	}

	/**
	 * 전체 원본글 목록
	 */
	public List<BoardVO> list() throws Exception{
		List<BoardVO> list = mapper.list();
		for (BoardVO vo : list) {
			vo.setFiles(attachMapper.getAttach(vo.getBno()));
		}
		return list;
	}

	/**
	 * @param bno - 상세보기 할 게시글 번호
	 * @return BoardVO - 게시글 번호와 일치하는 게시글 정보
	 */
	public BoardVO readBoard(int bno) throws Exception {
		BoardVO vo = mapper.readBoard(bno);
		// 첨부파일 목록 추가
		List<String> fileList = attachMapper.getAttach(bno);
		vo.setFiles(fileList);
		return vo;
	}

	/**
	 * bno가 일치하는 게시글의 조회수를 1 증가
	 * 기존 조회수에서 1증가 
	 */
	public void updateViewCnt(int bno) throws Exception{
		mapper.updateViewCnt(bno);
	}

	/**
	 * 답변글 등록
	 */
	public void replyRegister(BoardVO reply) throws Exception{
		// origin이 같은 그룹의 원본글 보다 아래쪽에 배치된 답변글을 한칸 아래쪽으로 배치 되도록 seq 값 수정
		mapper.updateReply(reply);
		
		// 원본글 한칸 아래
		reply.setSeq(reply.getSeq()+1);
		// 원본글에서 답글의 공백을 출력
		reply.setDepth(reply.getDepth()+1);
		
		// 답변글 등록
		mapper.replyRegister(reply);
	}
	
	
	public void modify(BoardVO board) throws Exception{
		// title, content, writer
		mapper.modify(board);
		
		// 수정하려는 게시글 기존에 등록되어 있던 첨부파일 정보 전체 삭제
		attachMapper.deleteAttach(board.getBno());
		// 변경된 첨부파일 목록 등록
		List<String> fileList = board.getFiles();
		if (fileList != null && !fileList.isEmpty()) {
			for (String fullName : fileList) {
				attachMapper.replaceAttach(board.getBno(), fullName);
			}
		}
	}

	@Transactional
	public void remove(int bno) throws Exception{
		// 첨부파일 db 삭제
		attachMapper.deleteAttach(bno);
		
		// re_tbl_board 게시글 정보 삭제
		mapper.remove(bno);
	}
	
	/**
	 * TODO board search BoardService check
	 * @since 2024-03-13
	 * @param SearchCriteria - 검색과 페이징 처리에 필요한 정보를 저장하는 객체 
	 * @return 검색과 페이징 처리된 게시글 목록과 페이지 블럭 출력을 위한 PageMaker를 Map으로 반환
	 */
	public Map<String, Object> listCriteria(SearchCriteria cri) throws Exception {
		List<BoardVO> list = mapper.listCriteria(cri);
		// 검색 범위내 전체 게시글 개수
		int totalCount = mapper.listCountCriteria(cri);
		
		// 검색된 정보로 페이징 블럭 정보 생성
		SearchPageMaker pm = new SearchPageMaker();
		pm.setCri(cri);
		pm.setTotalCount(totalCount);
		
		// 페이징 처리된 검색 게시글 목록과 페이징 블럭 정보를 맵으로 반환
		Map<String, Object> map = new HashMap<>();
		map.put("qnaList", list);
		map.put("pm", pm);
		return map;
	}
}
