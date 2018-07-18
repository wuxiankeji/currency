package com.mlink.base.common.configurer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

// 常用静态变量
@Configuration
public class MyConfig {
    @Value("${spring.profiles.active}")
    public String env;//当前激活的配置文件

}
