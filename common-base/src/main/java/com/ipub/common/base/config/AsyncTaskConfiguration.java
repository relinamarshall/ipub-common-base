package com.ipub.common.base.config;

import lombok.Data;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * AsyncTaskConfiguration
 *
 * @author wen.zhou
 * @since 2024/4/20
 */
@Data
@AutoConfiguration
@ConfigurationProperties(value = "com.ipub.thread")
public class AsyncTaskConfiguration implements AsyncConfigurer {
    /**
     * 获取当前机器的核数, 不一定准确 请根据实际场景 CPU密集 || IO 密集
     */
    public static final int CPU_NUM = Runtime.getRuntime().availableProcessors();
    /**
     * namePrefix
     */
    private String namePrefix = "Thread-Pool-";
    /**
     * corePoolSize
     */
    private Integer corePoolSize = CPU_NUM;
    /**
     * maxPoolSize
     */
    private Integer maxPoolSize = CPU_NUM * 2;
    /**
     * queueCapacity
     */
    private Integer queueCapacity = 500;
    /**
     * awaitTerminationSeconds
     */
    private Integer awaitTerminationSeconds = 60;

    @Override
    @Bean(name = "asyncExecutor")
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        // 核心线程大小 默认取 CPU 数量
        taskExecutor.setCorePoolSize(corePoolSize);
        // 最大线程大小 默认取 CPU * 2 数量
        taskExecutor.setMaxPoolSize(maxPoolSize);
        // 队列最大容量
        taskExecutor.setQueueCapacity(queueCapacity);
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        taskExecutor.setAwaitTerminationSeconds(awaitTerminationSeconds);
        taskExecutor.setThreadNamePrefix(namePrefix);
        taskExecutor.initialize();
        return taskExecutor;
    }
}
