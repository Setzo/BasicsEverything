package main;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component( "logger" )
public class Logger {

    @Before( "sayHiPointcut()" )
    public void aboutToSayHi() {
        System.out.println("About to say Hi.");
    }

    @After( "sayHiRunEverytime()" )
    public void saidHi() {
        System.out.println("Said hi.");
    }

    @Before( "sayHiStringPointcut()" )
    public void sayStrHi() {
        System.out.println("Only once");
    }

    @Pointcut( "execution(* main.*.*(..))" )
    public void sayHiPointcut() {
    }

    @Pointcut( "execution(void main.SayThings.sayHi())" )
    public void sayHiNoArgsPointcut() {
    }

    @Pointcut( "execution(* *.*(..))" )
    public void sayHiRunEverytime() {
    }

    @Pointcut( "execution(void main.SayThings.sayHi(String))" )
    public void sayHiStringPointcut() {
    }
}
