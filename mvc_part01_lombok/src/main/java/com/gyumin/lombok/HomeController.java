package com.gyumin.lombok;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		log.info("home controller binder");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(dateFormat, false));
		// 웹으로 전달되는 데이터가 패턴이 일치하면, Date 클래스로 변환 시켜줄 것이다 라는 의미
		// 한번에 처리하는 것( 패턴이 저렇게 들어오는 모든 데이터를 Date 클래스로 변환)
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		logger.info("Home Controller home 호출 : {}", new Date());
		return "home";
	}
	
	@GetMapping("user")
	public String user(UserVO user) {
		log.info("controller user : {}", user);
		return "home";
	}
}
