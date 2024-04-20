package com.ipub.common.base.aspect;

import com.ipub.common.base.util.JsonUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

/**
 * BaseAspect
 *
 * @author wen.zhou
 * @since 2024/4/20
 */
@Slf4j
public abstract class BaseAspect {
    /**
     * noIgnoreType
     *
     * @param v Object
     * @return boolean
     */
    protected boolean noIgnoreType(Object v) {
        return !(v == null || v instanceof HttpServletRequest || v instanceof HttpServletResponse || v instanceof InputStream || v instanceof OutputStream);
    }

    /**
     * aspect
     */
    public abstract void aspect();

    /**
     * handle
     *
     * @param pjp ProceedingJoinPoint
     * @return Object
     */
    public abstract Object handle(ProceedingJoinPoint pjp);

    /**
     * handle
     *
     * @return Object
     */
    @SneakyThrows
    public Object handle(String tag, ProceedingJoinPoint pjp) {
        log.info("------------------------------[{}] BEGIN------------------------------", tag.toUpperCase());
        long start = System.currentTimeMillis();
        RequestAttributes reqAttr = RequestContextHolder.getRequestAttributes();
        if (reqAttr instanceof ServletRequestAttributes inst) {
            HttpServletRequest req = inst.getRequest();
            log.info("client ip {}; http type {}, path {}.", req.getRemoteAddr(), req.getMethod(), req.getRequestURL());
        }
        Signature signature = pjp.getSignature();
        log.info("exec method {}.{}.", signature.getDeclaringTypeName(), signature.getName());
        Object[] param = Arrays.stream(pjp.getArgs()).filter(this::noIgnoreType).toArray();
        if (param.length > 0) {
            log.info("exec param {}.", JsonUtil.toJson(param));
        }
        Object result = pjp.proceed();
        if (noIgnoreType(result)) {
            log.info("exec result {}.", JsonUtil.toJson(result));
        }
        log.info("exec time {}ms.", System.currentTimeMillis() - start);
        log.info("------------------------------[{}] END------------------------------", tag.toUpperCase());
        return result;
    }
}

