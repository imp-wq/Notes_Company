package com.itniuma.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class MyAdvice {

    @Pointcut("execution(* com.itniuma.dao.BookDao.*(..))")
    private void pointCut() {
    }

    // @Before("pointCut()")
    public void before(JoinPoint joinPoint) {
        System.out.println("args: " + Arrays.toString(joinPoint.getArgs()));
        System.out.println("执行before方法");
    }

    // @After("pointCut()")
    public void after() {
        System.out.println("执行after方法");
    }

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("around before advice");
        Object[] args = proceedingJoinPoint.getArgs();
        args[0] += ",cba,nba";
        // 调用原始方法。
        Object result = proceedingJoinPoint.proceed(args);
        System.out.println("around after advice");
        return result;
    }

    @AfterReturning(value = "pointCut()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        System.out.println("----after returning-----");
        System.out.println("args: " + Arrays.toString(joinPoint.getArgs()));
        System.out.println("result: " + result);
        System.out.println("---------");
    }
}
