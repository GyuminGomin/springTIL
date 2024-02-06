package com.gyumin.di.vo;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component //@Service 사용은 되지만, 비추(만약에 사수가 보다가 쳐맞.. 수도 있다)
public class TestBoardVO {
	
	private int num;
	private String title;
	private String content;
	private int writer;
}
