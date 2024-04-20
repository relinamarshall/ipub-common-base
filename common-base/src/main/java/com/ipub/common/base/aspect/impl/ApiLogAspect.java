package com.ipub.common.base.aspect.impl;

import com.ipub.common.base.aspect.BaseAspect;
import lombok.SneakyThrows;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

/**
 * ApiLogAspect
 *
 * @author wen.zhou
 * @since 2024/4/20
 */
@Aspect
@AutoConfiguration
@ConditionalOnProperty(name = "com.ipub.aspect.api-log.enabled", havingValue = "true")
public class ApiLogAspect extends BaseAspect {
    @Override
    @Pointcut("@within(org.springframework.web.bind.annotation.RestController)")
    public void aspect() {
    }

    @Override
    @SneakyThrows
    @Around("aspect()")
    public Object handle(ProceedingJoinPoint pjp) {
        return super.handle("API", pjp);
    }
}
