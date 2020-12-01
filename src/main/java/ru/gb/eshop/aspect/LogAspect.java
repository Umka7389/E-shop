package ru.gb.eshop.aspect;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {

    @Around("@annotation(log)")
    protected Object logAround(ProceedingJoinPoint p, Log log) {
        System.out.println("Start operation");
        long startTime = System.currentTimeMillis();
        try {
            p.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        long totalTime = (System.currentTimeMillis() - startTime)/1000;
        System.out.println("Operation successful in: " + totalTime + " sec");

        return p;
    }
}
