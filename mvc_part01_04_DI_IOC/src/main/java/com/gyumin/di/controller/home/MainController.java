package com.gyumin.di.controller.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.gyumin.di.dao.TestDAO;
import com.gyumin.di.service.TestService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MainController {
	
	private final TestService ts;
	private final TestDAO td;
	
	/*
	public MainController(TestService ts, TestDAO td) {
		this.ts = ts;
		this.td = td;
	}
	*/
	
	@GetMapping("main")
	public String main() {
		System.out.println("MainController test Service : " + ts);
		return "home";
	}
}
