package com.gyumin.di.controller.home;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.gyumin.di.dao.TestDAO;
import com.gyumin.di.service.TestService;
import com.gyumin.di.vo.TestBoardVO;

import lombok.extern.slf4j.Slf4j;

/*
 * context:component-scan 으로 Bean 으로 등록되는 annotation
 * @Controller : Presentation Layer 에서 controller를 명시하기 위해서 사용
 * @Service : Business Layer 에서 Service 를 명시하기 위해서 사용
 * @Repository : Persistence Layer 에서 Data Access Object 또는 저장소를 명시하기 위해 사용
 * @Component : 그 외의 의미를 두지 않는 class를 Bean으로 등록하고 관리하기 위해 사용
 * @Component 의 하위 형태로 @Controller, @Service, @Repository 가 존재
 */

/*
 * DI Annotation (Dependency Injection) - 의존성 주입
 * spring에 의해서 관리되는 bean 객체를 우리가 원하는 필드에 주입받아 사용하게 해주는 annotation
 * 		Inject, named, resource는 1.8 부터는 annotation api가 들어가 있어야 사용 가능
 * 			@Autowired		@Qualifier				@Inject								@Resource
 * 	범용성	스프링 전용		스프링 전용				자바에서 지원							자바에서 지원
 * 	연결성	타입에 맞춰서 주입	Bean의 name을 이용해 주입	타입에 맞춰서 주입						이름으로 검색 후 타입으로 검색
 * 							독립적인 사용 x				@Named를 이용해 이름으로 구분해 주입 가능
 */

@Controller
@Slf4j
public class HomeController {

	@Autowired // 의존성 주입
	TestService ts;
	
	// @Autowired
	// private String uploadPath;
	@Resource(name="path")
	private String uploadPath;
	
	@Inject
	@Named("size")
	private int size;
	
	@Autowired
	@Qualifier(value="upload")
	private String uploadDir;
	
	@Autowired(required=false)
	private TestDAO td;
	
	@Autowired
	private TestBoardVO vo;
	
	@GetMapping("/")
	public String home() {
		log.info("{}",vo);
		log.info("uploadPath : {}", uploadPath);
		log.info("size : {}", size);
		log.info("uploadDir : {}", uploadDir);
		
		if (td != null) {
			td.select("Home Controller");
		} else {
			log.info("HomeController td is null");
		}
		return "home";
	}
	
}
