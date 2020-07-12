package com.icbc.zsyw.hope3;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;

@SpringBootApplication
@MapperScan("com.icbc.zsyw.hope3.mapper")
@EnableTransactionManagement
@EnableScheduling
//@ServletComponentScan(basePackages = "com.icbc.zsyw.hope3.config.*")
public class Hope3Application extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Hope3Application.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(Hope3Application.class, args);
    }
    /**
     * 配置上传文件大小的配置
     * @return
     */
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //  单个数据大小
        long bytes = 102400000L;
        factory.setMaxFileSize(DataSize.ofBytes(bytes));
        /// 总上传数据大小
        factory.setMaxRequestSize(DataSize.ofBytes(bytes));
        return factory.createMultipartConfig();
    }

}
