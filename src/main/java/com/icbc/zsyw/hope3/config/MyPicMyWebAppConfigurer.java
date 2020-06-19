package com.icbc.zsyw.hope3.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName MyPicMyWebAppConfigurer
 * @Description
 * @Author qinwankang
 * @Date 2020/6/3 15:03
 * @Version V1.0
 **/
@Configuration
public class MyPicMyWebAppConfigurer implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //外部访问路径映射到本地磁盘路径
        registry.addResourceHandler("/static/upload/**").addResourceLocations("file:D:\\icbc\\image\\");
    }
}
