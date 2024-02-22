package com.gyumin.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * pom.xml aspectjtools 의존성 추가 <br/>
 * servlet-context.xml <br/>
 * component-scan aop 패키지 추가 <br/>
 * aop:auto-proxy 태그 추가 <br/>
 */
@Aspect // AOPAdvice 클래스 임을 명시
@Slf4j
@Component
public class AOPAdvice {
	
	public AOPAdvice() {
		log.info("AOP Advice 생성");
	}
	
	// target joinPoint(method)가 실행 되기 전 호출 (execution({반환타입} {지정한 메소드 위치}))
	// @Before("execution(* com.bitc.service.*.*(..))") - 모든 반환타입, 트리의 모든 클래스중에서 모든 메소드 모든 매개변수(타입, 개수) (com.gyumin.service(트리) .*(모든클래스).*(모든메서드) (..)(모든매개변수)
	@Before("execution(* com.gyumin.service.*.*(..))")
	public void startLog(JoinPoint jp) {
		log.info("--------------------------------------");
		log.info("--------------------------------------");
		log.info("------------- START LOG --------------");
		log.info("target : {}", jp.getTarget()); // 우리가 패턴으로 지정한 클래스(execution)
		log.info("type : {}", jp.getKind()); // 타겟의 종류 알려줌 (호출된 대상자가 method면 method-execution)
		log.info("parameters : {}", Arrays.toString(jp.getArgs())); // 메소드의 매개변수는 여러개일 수 있으므로
		log.info("name : {}", jp.getSignature().getName()); // 타겟이 되는 joinpoint 메소드 이름
		log.info("----------- START LOG END ------------");
	}
	
	@After("execution(* com.gyumin.service.MessageServiceImpl.*(..))")
	public void endLog() {
		log.info("------------END AFTER LOG-------------");
		log.info("--------------------------------------");
	}
}
