package main;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component("logger")
public class Logger {

	@Before("sayHiTargetPointcut()")
	public void aboutToSayHi() {
		System.out.println("Before");
	}
	
	// Target - no wildcards
	@Pointcut("target(main.SayThings)")
	public void sayHiTargetPointcut() {
	}
	
	@Pointcut("within(main..*)")
	public void sayHiWithinPointcut() {
	}
	
	@Pointcut("this(main.TestI)")
	public void sayHiThisPointcut() {
	}
}
