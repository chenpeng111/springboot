package com.cpown.demo.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;

/**
 * druid私有化屬性配置
 */
@Configuration
public class DruidConfig {

    /**
     * 加載私有化配置，并重新返回数据源
     * ConfigurationProperties注解可以加载到配置信息
     * @return
     */
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource DruidDataSource(){
        return new DruidDataSource();
    };

    /**
     * 后台监控
     * //配置 Druid 监控管理后台的Servlet；
     * //内置 Servlet 容器时没有web.xml文件，所以使用 Spring Boot 的注册 Servlet 方式
     * @return
     */
    @Bean
    public ServletRegistrationBean ServletRegistrationBean(){
        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");

        HashMap<String,String> initHashMap = new HashMap<>();
        //设置账号密码  固定，不可变的
        initHashMap.put("loginUsername","admin");
        initHashMap.put("loginPassword","123456");
        //允许谁访问  “” 代表所有人
        initHashMap.put("allow","");

        bean.setInitParameters(initHashMap);
        return bean;
    }


    /**
     * 配置监控过滤器
     * @return
     */
    @Bean
    public FilterRegistrationBean get(){
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());
        HashMap<String,String> initHashMap = new HashMap<>();
        //配置哪些请求不需要监控
        initHashMap.put("exclusions","*.css,/druid/*");

        bean.setInitParameters(initHashMap);

        return bean;
    }

}
