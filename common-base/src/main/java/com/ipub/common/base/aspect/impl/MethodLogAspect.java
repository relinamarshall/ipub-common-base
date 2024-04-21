package com.ipub.common.base.aspect.impl;

import com.ipub.common.base.aspect.BaseAspect;
import lombok.SneakyThrows;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.boot.autoconfigure.AutoConfiguration;

/**
 * MethodLogAspect
 *
 * @author wen.zhou
 * @since 2024/4/20
 */
@Aspect
@AutoConfiguration
public class MethodLogAspect extends BaseAspect {
    @Override
    @Pointcut("@annotation(com.ipub.common.base.aspect.annotation.LogMethod)")
    public void aspect() {
    }

    @Override
    @SneakyThrows
    @Around("aspect()")
    public Object handle(ProceedingJoinPoint pjp) {
        return super.handle("METHOD", pjp);
    }
}
