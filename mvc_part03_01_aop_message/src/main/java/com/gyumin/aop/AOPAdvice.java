package com.gyumin.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gyumin.mapper.MessageMapper;
import com.gyumin.vo.MessageVO;

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
	/*
	
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
	
	@AfterThrowing(value = "execution(* com.gyumin.service.*.*(..))",
				   throwing = "exception")
	public void endThrowing(JoinPoint jp, Exception exception) {
		log.info("----------------------------------------");
		log.info("----------START @AfterThrowing----------");
		log.info("target : {}", jp.getTarget());
		log.info("name : {}", jp.getSignature().getName());
		log.warn("error : {}", exception.getMessage());
		log.info("---------END @AfterThrowing LOG---------");
		log.info("----------------------------------------");
	}
	
	// 타겟 메소드가 작업 수행 후 정상적으로 값을 반환 하고 난 뒤 수행
	@AfterReturning(
			pointcut="execution(!void com.gyumin.service.MessageServiceImpl.*(..))",
			returning = "returnValue"
			)
	public void successLog(JoinPoint jp, Object returnValue) {
		log.info("----------------------------------------");
		log.info("---------START @AfterReturning----------");
		log.info("target : {}", jp.getTarget());
		log.info("name : {}", jp.getSignature().getName());
		log.warn("return : {}", returnValue);
		log.info("--------END @AfterReturning LOG---------");
		log.info("----------------------------------------");
	}
	
	*/
	
	@Around("execution(* com.gyumin.service.*.*(..))")
	public Object serviceLog(ProceedingJoinPoint pjp) throws Throwable{
		log.info("----------------------------------------");
		log.info("------------ AROUND START --------------");
		log.info("target : {}", pjp.getTarget());
		log.info("name : {}", pjp.getSignature().getName());
		log.info("parameter : {}", Arrays.toString(pjp.getArgs()));
		
		// Before
		Object o = pjp.proceed();		// target 실체 객체의 pointcut method 호출
		// AFTER
		log.info("around AFTER : {}", o); // 실제 메서드가 반환한 결과
		log.info("-------------- AROUND END --------------");
		log.info("----------------------------------------");
		return o;
	}
	
	@Autowired
	MessageMapper mapper;
	
	@Around(value = "execution(* com.gyumin.service.MessageServiceImpl.readMessage(String, int)) && args(uid, mno)") // 매개변수로 전달해서 바로 사용할 수 있게(일일이 배열에서 가져오기 귀찮으므로)
	public Object checkReadMessage(ProceedingJoinPoint pjp, String uid, int mno) throws Throwable{
		
		log.info("-----------------------------------------------------");
		log.info("-----------checkReadMessage AROUND START-------------");
		log.info("parameter : {}", Arrays.toString(pjp.getArgs()));
		log.info("uid : {}", uid);
		log.info("mno : {}", mno);
		// 메시지 번호가 일치하는 메시지 정보를 MessageVO 타입으로 반환
		MessageVO vo = mapper.readMessage(mno);
		if (vo.getOpendate() != null) {
			log.info("throw readMessage : {}", vo);
			log.info("throw checkReadMessage AROUND END");
			throw new NullPointerException("이미 수신한 메시지 입니다.");
		}
		log.info("Before End ------------------------------------------");
		Object o = pjp.proceed();
		vo = (MessageVO)o;
		vo.setMessage("Arround에서 메시지 정보 변경");
		log.info("------------checkReadMessage AROUND END--------------");
		return vo;
	}
}
