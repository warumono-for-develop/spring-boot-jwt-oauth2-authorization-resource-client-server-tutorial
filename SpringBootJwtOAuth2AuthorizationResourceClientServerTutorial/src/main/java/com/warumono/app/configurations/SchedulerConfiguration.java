package com.warumono.app.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@EnableScheduling
@Configuration
public class SchedulerConfiguration
{
////	http://jsonobject.tistory.com/236

	/*
	//// 단일 쓰레드로 충분하다면 ConcurrentTaskScheduler
	//// 단일 쓰레드로 관리될 경우, 2개 이상의 작업을 같은 시간에 실행되도록 설정시 1개 작업을 먼저 실행하여 완료 후 다른 작업을 순차적으로 실행
	@Bean
	public TaskScheduler taskScheduler()
	{
		return new ConcurrentTaskScheduler();
	}
	/*/
	//// 쓰레드 풀이 필요하다면 ThreadPoolTaskScheduler
	//// 여러 작업이 동시에 실행되기를 원할 경우 쓰레드 풀의 크기를 2개 이상으로 설정
	@Bean
	public TaskScheduler taskScheduler()
	{
		ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
		taskScheduler.setPoolSize(10);
		
		return taskScheduler;
	}
	//*/
}
