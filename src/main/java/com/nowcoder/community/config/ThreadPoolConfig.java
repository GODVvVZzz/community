package com.nowcoder.community.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author VvV
 * @date 2022/10/18
 */
@Configuration
@EnableAsync
@EnableScheduling
public class ThreadPoolConfig {
}
