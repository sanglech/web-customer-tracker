package com.christian.springdemo.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class CRMLoggingAspect {
	
	//setup logger
	
	private Logger myLogger= Logger.getLogger(getClass().getName());	
	//setup pointcut declarations
	
	@Pointcut("execution(* com.christian.springdemo.controller.*.*(..))")
	private void forControllerPackage() {}
	
	@Pointcut("execution(* com.christian.springdemo.service.*.*(..))")
	private void forServicePackage() {}
	
	
	@Pointcut("execution(* com.christian.springdemo.DAO.*.*(..))")
	private void forDAOPackage() {}
	
	
	
	@Pointcut("forControllerPackage() ||forServicePackage() ||forDAOPackage() ")
	private void  forAppFlow(){}
	
	
	//setup before advice
	@Before("forAppFlow()")
	public void before(JoinPoint theJoinPoint) {
		//display the method we are calling
		
		String theMethod= theJoinPoint.getSignature().toShortString();
		myLogger.info("=========> in @Before: calling method "+ theMethod);
		
		//display the args
		
		//get the arguements
		Object [] args= theJoinPoint.getArgs();
		
		//loop through and display the arguements
		for(Object arg: args) {
			myLogger.info("=======> arguement: "+arg);
		}
		
		
	}
	//setup after returning advice
}
