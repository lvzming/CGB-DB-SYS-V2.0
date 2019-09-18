package com.tedu.cgb.team.common.config;

import java.lang.reflect.Method;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import lombok.extern.slf4j.Slf4j;

@SuppressWarnings("unused")
@Slf4j
@Configuration
public class SpringAsyncConfiguration implements AsyncConfigurer {
	private int corePoolSize = 2;
	private int maxPoolSize = 5;
	private int keepAliveSeconds = 60 * 10;
	private String threadNamePrefix = "sys-async-thread-";
	private int queueCapacity = 5;
	
	private final ThreadFactory threadFactory = new ThreadFactory() {
		private AtomicLong count = new AtomicLong(1);
		@Override
		public Thread newThread(Runnable r) {
			return new Thread(r, "sys-async-thread-" + count.getAndIncrement());
		}
	};
	
	/**
	 * 使用SimpleAsyncTaskExecutor对象，特点：
	 * 1）每次新请求都会创建新的线程；
	 * 2）可以对并发请求进行限流操作（原理：底层进行阻塞操作）
	 */
	@Override
	public Executor getAsyncExecutor() {
//		SimpleAsyncTaskExecutor taskExecutor = new SimpleAsyncTaskExecutor();
//		taskExecutor.setConcurrencyLimit(100);
		ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
		// 设置核心线程数
		taskExecutor.setCorePoolSize(corePoolSize);
		// 设置最大线程数
		taskExecutor.setMaxPoolSize(maxPoolSize);
		// 设置线程空闲时间
		taskExecutor.setKeepAliveSeconds(keepAliveSeconds);
		// 设置线程对象名字前缀
		taskExecutor.setThreadNamePrefix(threadNamePrefix);
		// 设置队列最大容量
		taskExecutor.setQueueCapacity(5);
		// 设置拒绝处理的策略（当池无法处理新的任务时，该执行什么策略处理）
		// 在这里默认选择调用者线程（例如tomcat线程池，
		taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
		// 进行池的初始化
		taskExecutor.initialize();
		return taskExecutor;
	}
	
	@Override
	public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
		AsyncUncaughtExceptionHandler exceptionHandler = 
				(ex, method, params) -> {
			log.error("执行异步任务时出现了未知错误");
		};
		
		return exceptionHandler;
	}
	
	public Executor asyncExecutor() {
		LinkedBlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>();
		ThreadPoolExecutor taskExecutor = 
				new ThreadPoolExecutor(
						corePoolSize,
						maxPoolSize,
						keepAliveSeconds,
						TimeUnit.SECONDS, 
						workQueue, 
						threadFactory);
		return taskExecutor;
	}
	
}
