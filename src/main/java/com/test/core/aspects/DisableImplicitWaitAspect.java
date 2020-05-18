package com.test.core.aspects;

import com.test.core.entities.ImplicitlyWaitSwitch;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
@Log4j2
public class DisableImplicitWaitAspect {
    private ImplicitlyWaitSwitch implicitlyWaitSwitch = new ImplicitlyWaitSwitch();

    @SneakyThrows
    @Around("execution(@com.test.core.annotations.DisableImplicitWait * *.*(..))")
    public Object disableImplicitWaitAdvice(ProceedingJoinPoint pjp) {
        if (!implicitlyWaitSwitch.isImplicitlyWaitDisabled()) {
            implicitlyWaitSwitch.disableImplicitlyWait();
            log.warn("Implicit wait was disabled for {}", pjp.getSourceLocation());

            Object returnResult = pjp.proceed(pjp.getArgs());
            implicitlyWaitSwitch.enableImplicitlyWait();
            log.warn("Implicit wait was enabled for {}", pjp.getSourceLocation());

            return returnResult;
        } else {
            return pjp.proceed(pjp.getArgs());
        }
    }
}
