package com.gyumin.common.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class CommonAdvice {
	
	/**
	 * 반환타입과 매개변수에 상관 없이 com.gyumin.*.controller package에 있는 <br/>
	 * 모든 class를 타겟으로 지정하고 모든 method를 joinPoints로 지정
	 */
	@Around("execution(* com.gyumin.*.controller.*.*(..))")
	public Object checkControllerLog(ProceedingJoinPoint pjp) throws Throwable {
		log.info("===================================================");
		log.info("---------- Adivce CheckController START -----------");
		log.info("target : {} ", pjp.getTarget());
		log.info("method : {} ", pjp.getSignature().getName());
		log.info("Arguments : {} ", Arrays.toString(pjp.getArgs()));
		Object o = pjp.proceed();
		log.info("return : {} ", o);
		log.info("----------- Adivce CheckController END ------------");
		log.info("===================================================");
		return o;
	}
	
	@Around("execution(* com.gyumin.*.service.*.*(..))")
	public Object checkServiceLog(ProceedingJoinPoint pjp) throws Throwable {
		log.info("===================================================");
		log.info("------------ Adivce CheckService START ------------");
		log.info("target : {} ", pjp.getTarget());
		log.info("method : {} ", pjp.getSignature().getName());
		log.info("Arguments : {} ", Arrays.toString(pjp.getArgs()));
		Object o = pjp.proceed();
		log.info("return : {} ", o);
		log.info("------------- Adivce CheckService END -------------");
		log.info("===================================================");
		return o; 
	}
}
