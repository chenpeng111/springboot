package com.cpown.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * create by c-pown on 2020-07-28
 */
@Configuration
@Slf4j
public class ShiroConfig {

    /**
     * Shiro：三大组件：Subject,SecurityManager,Realm
     *
     */
    @Bean
    public MyRealm myRealm(){
        log.info("注册Realm");
        return new MyRealm();
    }

    /**
     * 创建SecurityManager 安全管理器组件
     * @param realm
     * @return
     */
    @Bean
    public SecurityManager securityManager(@Qualifier("myRealm")MyRealm realm){
        log.info("创建SecurityManager");
        DefaultWebSecurityManager  securityManager = new DefaultWebSecurityManager(realm);
        return securityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("securityManager")SecurityManager securityManager){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(securityManager);
        Map<String, String> map = new LinkedHashMap<>();
        //anon标识可以直接放行
        map.put("/index","anon");
//        //authc标识需要认证
//        map.put("user/**", "authc");
//        map.put("department/**", "authc");

        bean.setFilterChainDefinitionMap(map);
        bean.setLoginUrl("/sys/login");
        return bean;
    }



}