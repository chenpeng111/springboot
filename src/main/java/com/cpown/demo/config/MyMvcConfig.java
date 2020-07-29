package com.cpown.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 配置中心
 */
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {
    /**
     * 配置转发器
     * @param registry
     */
//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        //可以将制定路径转发到别的页面    /  默认转发到 index页面
//        registry.addViewController("/").setViewName("index");
//        //index.html  默认也转发到 index页面
//        registry.addViewController("/index.html").setViewName("index");
//        registry.addViewController("/main.html").setViewName("dashboard");
//
//    }
//
//    /**
//     * 自定义拦截器
//     * @param registry
//     */
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        //拦截所有请求  除了 "/index","/user/login","/",/index.html
//        //同时也要 排除拦截 静态资源 否则页面样式功能无法加载
//        registry.addInterceptor(new LoginHandlerInterceptor())
//                .addPathPatterns("/**")
//                .excludePathPatterns("/index.html","/index","/sys/login","/","/css/*","/js/*","/img/*");
//    }
}
