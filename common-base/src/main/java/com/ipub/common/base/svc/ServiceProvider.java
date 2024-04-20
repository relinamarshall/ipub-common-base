package com.ipub.common.base.svc;

import java.util.function.Consumer;

/**
 * 服务提供容器
 *
 * @author wen.zhou
 * @since 2024/4/20
 */
public interface ServiceProvider {

    /**
     * 获取服务
     *
     * @param type 服务类型
     * @param <T>  服务类型
     * @return 服务对象
     */
    static <T> T service(Class<T> type) {
        return service(type, true);
    }

    /**
     * 获取服务
     *
     * @param type     服务类型
     * @param required 未找到时是否抛出异常
     * @param <T>      服务类型
     * @return 服务对象
     */
    static <T> T service(Class<T> type, boolean required) {
        return global().getService(type, required);
    }

    /**
     * 添加服务到全局容器
     *
     * @param svcList 服务对象列表
     */
    static void add(Object... svcList) {
        for (Object svc : svcList) {
            ServiceProviderContext.INST.addService(svc);
        }
    }

    /**
     * 添加服务
     *
     * @param svc           服务对象
     * @param uniqueTypeKey 服务唯一类型键
     */
    static void add(Object svc, Class<?> uniqueTypeKey) {
        ServiceProviderContext.INST.addService(svc, uniqueTypeKey);
    }

    /**
     * 获取全局服务提供容器
     *
     * @return 服务提供容器
     */
    static ServiceProvider global() {
        return ServiceProviderContext.INST;
    }

    /**
     * 创建服务提供容器实例
     *
     * @return 服务提供容器
     */
    static ServiceProvider createInstance() {
        return ServiceProviderContext.createInstance();
    }

    /**
     * 添加服务
     *
     * @param svc 服务对象
     */
    void addService(Object svc);

    /**
     * 添加服务
     *
     * @param svc           服务对象
     * @param uniqueTypeKey 服务唯一类型键
     */
    void addService(Object svc, Class<?> uniqueTypeKey);

    /**
     * 获取服务
     *
     * @param type 服务类型
     * @param <T>  服务类型
     * @return 服务对象
     */
    <T> T getService(Class<T> type);

    /**
     * 获取服务
     *
     * @param type     服务类型
     * @param required 是否强制获取服务
     * @param <T>      服务类型
     * @return 服务对象
     */
    <T> T getService(Class<T> type, boolean required);

    /**
     * 移除服务
     *
     * @param svc 服务对象
     */
    void removeService(Object svc);

    /**
     * 遍历所有服务
     *
     * @param consumer 遍历消费者
     */
    void forEach(Consumer<Object> consumer);

    /**
     * 判断服务是否存在
     *
     * @param svc 服务对象
     * @return 是否存在
     */
    boolean exists(Object svc);
}
