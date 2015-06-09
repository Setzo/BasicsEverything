package main;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component("logger")
public class Logger {

	@Before("pointcut()")
	public void aboutToSayHi() {
		System.out.println("Before");
	}
	
	@After("p2()")
	public void saidHi() {
		System.out.println("After");
	}
	
	@Pointcut("@target(org.springframework.stereotype.Component)")
	public void pointcut() {
	}
	
	@Pointcut("@annotation(java.lang.Deprecated)")
	public void p2() {
	}
	
	@Pointcut("@args(org.springframework.stereotype.Component)")
	public void p3() {
	}
}
