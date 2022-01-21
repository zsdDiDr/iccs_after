package com.example.iccs.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 解决异步访问跨域
 *
 * @author zsd
 * @date 2022/1/13
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //本应用的所有方法都会去处理跨域请求
        registry.addMapping("/**")
                //允许远端访问的域名
                .allowedOrigins("http://localhost:8080")
                //允许请求的方法("POST", "GET", "PUT", "OPTIONS", "DELETE")
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
                //允许请求头
                .allowedHeaders("*")
                .allowCredentials(true);

        logger.info("异步访问跨域请求处理开启成功");
    }
}

