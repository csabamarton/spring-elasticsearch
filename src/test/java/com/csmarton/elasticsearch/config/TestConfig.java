package com.csmarton.elasticsearch.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.csmarton.elasticsearch.mapper")
public class TestConfig {
}