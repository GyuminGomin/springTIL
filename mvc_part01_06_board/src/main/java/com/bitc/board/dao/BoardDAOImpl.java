package com.bitc.board.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.bitc.board.util.Criteria;
import com.bitc.board.vo.BoardVO;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class BoardDAOImpl implements BoardDAO {

	private final SqlSession session;
	
	@Override
	public int create(BoardVO vo) throws Exception {
		int result = session.insert("boardMapper.insertBoard", vo);
		return result;
	}

	@Override
	public BoardVO read(int bno) throws Exception {
		BoardVO board = session.selectOne("boardMapper.boardDetail", bno);
		return board;
	}

	@Override
	public int update(BoardVO vo) throws Exception {
		int result = session.update("boardMapper.updateBoard", vo);
		return result;
	}

	@Override
	public int delete(int bno) throws Exception {
		int result = session.delete("boardMapper.deleteBoard", bno);
		return result;
	}

	@Override
	public void updateCnt(int bno) throws Exception {
		session.update("boardMapper.viewcnt", bno);
	}

	@Override
	public int totalCount() throws Exception {
		int result = session.selectOne("boardMapper.totalCount");
		return result;
	}

	@Override
	public List<BoardVO> listCriteria(Criteria cri) throws Exception {
		List<BoardVO> list = session.selectList("boardMapper.listPageCri", cri);
		return list;
	}

}
