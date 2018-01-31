package com.hu.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * 定时任务
 */
@EnableScheduling
@Configuration
public class SchedulingConfig {
    @Scheduled(cron = "0/60 * * * * ?") // 每20秒执行一次
    public void scheduler() {
        System.out.println("定时任务开始执行。。。。。。。。。。。。");
        System.out.println(">>>>>>>>> SchedulingConfig.scheduler()");
    }
}
