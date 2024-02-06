package com.gyumin.lombok;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.Singular;
import lombok.ToString;

// @Data
// @Getter
// @Setter
// @ToString(exclude = {"uid","upw"}, callSuper = true) // 부모 toString도 호출, toString에 해당 여러 필드 제외
// 객체 생성 시 필요한 필수 값이 존재하기 때문에 기본 생성자로 객체 생성 불가
// @NoArgsConstructor
//@AllArgsConstructor
//@EqualsAndHashCode(of = {"uid","upw"}) // 지정한 필드만 포함
//@RequiredArgsConstructor // 필수 인자값을 이용한 생성자 (uid, upw, uname)
@Builder
@ToString
public class UserVO {
	
	@Getter
	private int uno;
	@Setter @NonNull
	private String uid;
	@NonNull
	private String upw;
	private final String uname;
	
	// @DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	private Date regdate;
	
	@Singular("list")
	private List<String> friendList;
}
