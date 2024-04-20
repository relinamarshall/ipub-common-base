package com.ipub.common.base.svc;

import com.ipub.common.base.util.AssertUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * 服务提供容器实现
 *
 * @author wen.zhou
 * @since 2024/4/20
 */
@Slf4j
final class DefaultServiceProvider implements ServiceProvider {
    /**
     * service type map
     */
    private final Map<Class<?>, Object> serviceMap;

    /**
     * 构造方法，初始化serviceMap
     */
    DefaultServiceProvider() {
        this.serviceMap = new HashMap<>();
    }

    /**
     * 添加服务
     *
     * @param svc 服务对象
     */
    public void addService(Object svc) {
        this.addService(svc.getClass(), svc, true);
    }

    /**
     * 添加服务
     *
     * @param svc           服务对象
     * @param uniqueTypeKey 服务唯一类型键
     */
    public void addService(Object svc, Class<?> uniqueTypeKey) {
        AssertUtil.assertTrue(uniqueTypeKey.isAssignableFrom(svc.getClass()),
                "service instance must be as type:" + uniqueTypeKey);
        this.addService(uniqueTypeKey, svc, false);
    }

    /**
     * 获取服务
     *
     * @param type 服务类型
     * @param <T>  服务类型
     * @return 服务对象
     */
    public <T> T getService(Class<T> type) {
        return getService(type, true);
    }

    /**
     * 获取服务
     *
     * @param type     服务类型
     * @param required 是否强制获取服务
     * @param <T>      服务类型
     * @return 服务对象
     */
    @SuppressWarnings("unchecked")
    public <T> T getService(Class<T> type, boolean required) {
        Object svc = this.serviceMap.get(type);
        AssertUtil.assertTrue(null != svc || !required, "not found the service type instance:" + type);
        return (T) svc;
    }

    /**
     * 移除服务
     *
     * @param svc 服务对象
     */
    @Override
    public void removeService(Object svc) {
        ArrayList<Class<?>> keys = new ArrayList<>();
        this.serviceMap.forEach((key, value) -> {
            if (value == svc) {
                keys.add(key);
            }
        });
        keys.forEach(this.serviceMap::remove);
    }

    /**
     * 遍历所有服务
     *
     * @param consumer 遍历消费者
     */
    @Override
    public void forEach(Consumer<Object> consumer) {
        Object[] items = this.serviceMap.values().toArray();
        for (Object item : items) {
            consumer.accept(item);
        }
    }

    /**
     * 是否存在服务
     *
     * @param svc 服务对象
     * @return 是否存在
     */
    @Override
    public boolean exists(Object svc) {
        return this.serviceMap.containsValue(svc);
    }

    /**
     * 添加服务
     *
     * @param type      服务类型
     * @param svc       服务对象
     * @param recursive 是否递归添加接口和服务类型父类的引用
     */
    private void addService(Class<?> type, Object svc, boolean recursive) {
        if (null == type || type.equals(Object.class)) {
            return;
        }
        if (this.serviceMap.containsKey(type)) {
            log.warn("already exists service instance with type:" + type);
        }
        log.debug("add service instance for type:{}", type);
        this.serviceMap.put(type, svc);

        if (recursive) {
            for (Class<?> typeInterface : type.getInterfaces()) {
                addService(typeInterface, svc, recursive);
            }
            this.addService(type.getSuperclass(), svc, recursive);
        }
    }
}
