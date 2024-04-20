package com.ipub.common.base.context;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * SpringContextHolder
 *
 * @author wen.zhou
 * @since 2024/4/20
 */
@Slf4j
@Service
@Lazy(false)
@SuppressWarnings("unchecked")
public class SpringContextHolder implements ApplicationContextAware, DisposableBean {
    /**
     * applicationContext
     */
    @Getter
    private static ApplicationContext applicationContext = null;

    /**
     * 获取Bean
     */
    public static <T> T getBean(String name) {
        return (T) applicationContext.getBean(name);
    }

    /**
     * 获取Bean
     */
    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

    /**
     * 发布事件
     *
     * @param event ApplicationEvent
     */
    public static void pushEvent(ApplicationEvent event) {
        if (applicationContext == null) {
            return;
        }
        applicationContext.publishEvent(event);
    }

    /**
     * 清除Context
     */
    public static void clearHolder() {
        if (log.isDebugEnabled()) {
            log.debug("clear SpringContextHolder {}.", applicationContext);
        }
        applicationContext = null;
    }

    /**
     * 注入Context
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        SpringContextHolder.applicationContext = applicationContext;
    }

    /**
     * Context关闭时清理静态变量
     */
    @Override
    public void destroy() {
        SpringContextHolder.clearHolder();
    }
}
