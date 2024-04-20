package com.ipub.common.base.aspect.impl;

import com.ipub.common.base.aspect.BaseAspect;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

/**
 * MethodLogAspect
 *
 * @author wen.zhou
 * @since 2024/4/20
 */
@Aspect
@AutoConfiguration
@ConditionalOnProperty(name = "com.ipub.aspect.method-log.enabled", havingValue = "true")
public class MethodLogAspect extends BaseAspect {
    @Override
    @Pointcut("@annotation(com.ipub.common.base.aspect.annotation.LogMethod)")
    public void aspect() {
    }

    @Override
    @Around("aspect()")
    public Object handle(ProceedingJoinPoint pjp) throws Throwable {
        return super.handle("METHOD", pjp);
    }
}
