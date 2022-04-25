package com.java1234.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 配置读取电脑本地资源文件路径
 */
@Configuration
public class MyWebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //获取项目根目录
        String strRootPath = "D:";

        //意思就是,前端浏览器访问路径带有/file/**就转到对应磁盘下读取图片,
        //类似前端访问tomcat webapp下file文件夹中文件

        //项目根目录下配置文件访问路径
        registry.addResourceHandler("/**").addResourceLocations("file:" + strRootPath + "/");
    }
}