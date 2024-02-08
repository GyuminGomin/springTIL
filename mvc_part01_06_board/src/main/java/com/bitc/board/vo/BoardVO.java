package com.bitc.board.vo;

import java.util.Date;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class BoardVO {
	private int bno;
	private final String title;
	private final String content;
	private final String writer;
	private Date regdate;
	private int viewcnt;
}
