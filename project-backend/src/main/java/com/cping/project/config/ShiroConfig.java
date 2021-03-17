package com.cping.project.config;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.apache.shiro.web.filter.mgt.DefaultFilter.anon;
import static org.apache.shiro.web.filter.mgt.DefaultFilter.authc;

@Configuration
public class ShiroConfig {

    // third
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(defaultWebSecurityManager);

        Map<String, String> filterMap = new LinkedHashMap<>();
        filterMap.put("user", "roles[admin]");
        //filterMap.put("/login","anon");
        filterMap.put("/","authc");
        //filterMap.put("/welcome", "roles[admin,user]");


        System.out.println("Shiro拦截器工厂类注入成功");
        bean.setFilterChainDefinitionMap(filterMap);
        bean.setLoginUrl("/login");
        bean.setUnauthorizedUrl("/noauth");

        return bean;
    }
    //second
    //DefaultWebSecurityManager
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecuritymanager(@Qualifier("userRealm") UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //SecurityUtils.setSecurityManager(securityManager);
        securityManager.setRealm(userRealm);
        return securityManager;
    }
    //first
    @Bean
    public UserRealm userRealm(){
        return new UserRealm();
    }
}
