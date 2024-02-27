package com.gyumin.mvc.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

import org.springframework.beans.factory.annotation.Autowired;

import com.gyumin.mvc.vo.TestVO;

import lombok.extern.slf4j.Slf4j;

@WebFilter(
	urlPatterns = "/*",
	initParams = {@WebInitParam(name="encoding", value="UTF-8")}
)
@Slf4j
public class PrintFilter implements Filter{

	@Autowired(required = false)
	TestVO test;
	
	private String encodingName; 
	
	/**
	 * 필터 초기화 될 때 한 번 실행
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.info("PrintFilter 초기화 시작 ===");
		encodingName = filterConfig.getInitParameter("encoding");
		log.info("PrintFilter 초기화 종료 ===");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		log.info("PrintFilter doFilter START ============================");
		log.info("filter testVO injection : {}", test);
		request.setCharacterEncoding(encodingName);
		chain.doFilter(request, response);
		log.info("PrintFilter doFilter END ==============================");
	}
	
	/**
	 * 필터가 메모리에서 삭제되기 전 한 번 실행
	 */
	@Override
	public void destroy() {
		log.info("PrintFilter destroy() ===");
	}
}
