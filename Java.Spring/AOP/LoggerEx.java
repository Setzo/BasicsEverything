package main;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component("logger")
public class Logger {

	@Before("sayHiPointcut()")
	public void aboutToSayHi() {
		System.out.println("Before");
	}
	
	@Pointcut("execution(* main.SayThings.sayHi())")
	public void sayHiPointcut() {
	}
	
	@After("sayHiPointcut()")
	public void afterHi() {
		System.out.println("After");
	}

	@AfterReturning("sayHiPointcut()")
	public void noExceptions() {
		System.out.println("No exceptions caught");
	}
	
	@AfterThrowing("sayHiPointcut()")
	public void withExceptions() {
		System.out.println("Exception was thrown");
	}
	
	@Around("sayHiPointcut()")
	public void around(ProceedingJoinPoint p) {
		
		System.out.println("Around (before)");
		try {
			p.proceed();
		} catch (Throwable e) {
			System.out.println(e.getMessage());
		}
		System.out.println("Around (after)");
	}
}
