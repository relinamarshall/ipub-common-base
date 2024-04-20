package com.ipub.common.base.svc;

import com.ipub.common.base.json.FastJsonImpl;
import com.ipub.common.base.json.Json;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Supplier;

/**
 * ServiceProviderContext
 *
 * @author wen.zhou
 * @since 2024/4/20
 */
@Slf4j
final class ServiceProviderContext {

    /**
     * 服务提供容器实例
     */
    static final ServiceProvider INST;

    static {
        // 创建服务容器实例
        INST = createInstance();
        loadDefaultService();
    }

    /**
     * 私有构造函数
     */
    private ServiceProviderContext() {
    }

    /**
     * 创建服务容器实例
     *
     * @return 服务容器实例
     */
    static ServiceProvider createInstance() {
        // TODO 增加扩展的服务提供者
        // ServiceProvider extProvider = SpringContextHolder.getBean(ServiceProvider.class);
        return new DefaultServiceProvider();
    }

    /**
     * 加载默认服务
     */
    private static void loadDefaultService() {
        tryLoadService(Json.class, FastJsonImpl::new);
    }

    /**
     * 尝试加载默认服务
     *
     * @param svcType   服务类型
     * @param fnNewInst 实例创建函数
     */
    static <T> void tryLoadService(Class<T> svcType, Supplier<T> fnNewInst) {
        T inst = tryCreateService(svcType, fnNewInst);
        if (null == inst) {
            return;
        }
        INST.addService(inst);
    }

    /**
     * 尝试加载默认服务
     *
     * @param svcType   服务类型
     * @param fnNewInst 实例创建函数
     */
    static <T> T tryCreateService(Class<T> svcType, Supplier<T> fnNewInst) {
        if (INST.getService(svcType, false) != null) {
            return null;
        }
        try {
            return fnNewInst.get();
        } catch (Exception ex) {
            log.warn("load default service {} failed! {}", svcType, ex.toString());
            return null;
        }
    }
}