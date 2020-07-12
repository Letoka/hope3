package com.icbc.zsyw.hope3.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @ClassName MyPicMyWebAppConfigurer
 * @Description
 * @Author qinwankang
 * @Date 2020/6/3 15:03
 * @Version V1.0
 **/
@Configuration
public class MyPicMyWebAppConfigurer implements WebMvcConfigurer  {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //外部访问路径映射到本地磁盘路径--windows系统映射
      /* registry.addResourceHandler("/static/upload/**")
               .addResourceLocations("file:D:\\icbc\\image\\")
               .addResourceLocations("file:D:\\icbc\\image\\moduleicon\\")
               .addResourceLocations("file:D:\\icbc\\image\\moduleimage\\")
               .addResourceLocations("file:D:\\icbc\\image\\imagebar\\")
               .addResourceLocations("file:D:\\icbc\\image\\shortcutimage\\")
                .addResourceLocations("file:D:\\icbc\\image\\activity\\");*/
        //外部访问路径映射到本地磁盘路径--Linux系统映射
        //registry.addResourceHandler("/static/upload/**").addResourceLocations("file:/data/tmp/image/");
      registry.addResourceHandler("/static/upload/**").addResourceLocations("file:/data/mobile/article/")
                .addResourceLocations("file:/data/mobile/article/pictures/")
                .addResourceLocations("file:/data/mobile/resource/")
                .addResourceLocations("file:/data/mobile/resource/assets/")
                .addResourceLocations("file:/data/mobile/resource/assets/images/")
                .addResourceLocations("file:/data/mobile/resource/index/")
                .addResourceLocations("file:/data/mobile/resource/my2019/");
        //外部访问路径映射到项目路径--windows系统映射
        //外部访问路径映射到项目路径--Linux系统映射
        //registry.addResourceHandler("/static/upload/**").addResourceLocations("classpath:/static/");
        //registry.addResourceHandler("/static/upload/**").addResourceLocations("classpath:/META-INF/resources/");
    }
}
