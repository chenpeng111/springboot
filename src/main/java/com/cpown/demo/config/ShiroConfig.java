package com.cpown.demo.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
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
 * ShiroConfig 配置类
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
        //anon标识可以直接放行 首页登录index直接放行
        map.put("/index","anon");
        map.put("/sys/toLogin","anon");
        /**
         *  shiro内置过滤器
         *  anon  无需认证可以访问
         *  authc  必须认证才能访问
         *  user  必须拥有  记住我 功能 才可以用
         *  perms 拥有对某个资源的权限才能访问
         *  role 拥有某个角色才能访问
         */
        map.put("/user/**", "authc");
        map.put("/department/**", "authc");
        map.put("/role/**", "authc");
        map.put("/perm/**", "authc");
        // “/role/**” 开头的用户需要角色认证，是“管理”才允许
        map.put("/role/**", "roles[管理]");
        // “/perm/**” 开头的用户需要权限认证，是create才允许
        map.put("/perm/**", "perms[create]");

        bean.setFilterChainDefinitionMap(map);
        //登录页
        bean.setLoginUrl("/sys/toLogin");
        //未授权跳转页
        bean.setUnauthorizedUrl("/sys/toUnAuthorized");
        return bean;
    }

    @Bean(name = "shiroDialect")
    public ShiroDialect shiroDialect(){
        return new ShiroDialect();
    }
}
