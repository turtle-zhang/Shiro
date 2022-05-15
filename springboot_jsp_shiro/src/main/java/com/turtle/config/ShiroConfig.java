package com.turtle.config;

import com.turtle.cache.RedisCacheManager;
import com.turtle.realm.CustomerRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * 用来整合shiro框架相关的配置类
 */
@Configuration
public class ShiroConfig {

    /**
     * 1.创建shiroFilter  //负责拦截所有请求
     *
     * @param defaultWebSecurityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager) {

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //给filter设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);

        //配置系统受限资源-配置系统公共资源
        Map<String, String> map = new HashMap<String, String>();

        // 需要放开认证的资源
        // anon 设置为公共资源  放行资源放在下面
        // login.jsp 可以不需要设置为anon，因为下面设置了认证不通过时跳转到login.jsp了
        map.put("/user/login", "anon");
        map.put("/user/register", "anon");
        map.put("/register.jsp", "anon");
        // 放行验证码
        map.put("/user/getImage", "anon");

        // authc 请求这个资源需要认证和授权
        // user:对应的是使用了记住我
        map.put("/**", "user");

        //默认认证界面路径---当认证不通过时跳转
        shiroFilterFactoryBean.setLoginUrl("/login.jsp");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }

    /**
     * 2.创建安全管理器
     *
     * @param realm
     * @return
     */
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("Realm") Realm realm, @Qualifier("rememberMeManager") CookieRememberMeManager cookieRememberMeManager) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        //给安全管理器设置
        defaultWebSecurityManager.setRealm(realm);
        defaultWebSecurityManager.setRememberMeManager(cookieRememberMeManager);
        return defaultWebSecurityManager;
    }


    /**
     * 3.创建自定义realm(name = "Realm")
     *
     * @return
     */
    @Bean(name = "Realm")
    public Realm getRealm() {
        // 使用我们自定义的realm
        CustomerRealm customerRealm = new CustomerRealm();
        // 设置hashed凭证匹配器
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        // 设置md5加密
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        // 设置1024次散列
        hashedCredentialsMatcher.setHashIterations(1024);
        customerRealm.setCredentialsMatcher(hashedCredentialsMatcher);


        //开启缓存管理器 [这个是Shiro的自带缓存，不建议使用]
//        customerRealm.setCachingEnabled(true);
//        customerRealm.setAuthorizationCachingEnabled(true);
//        customerRealm.setAuthorizationCachingEnabled(true);
//        customerRealm.setCacheManager(new EhCacheManager());

        customerRealm.setCachingEnabled(true);
        customerRealm.setAuthorizationCachingEnabled(true);
        customerRealm.setAuthorizationCachingEnabled(true);
        customerRealm.setCacheManager(new RedisCacheManager());
        return customerRealm;
    }

    /**
     * 记住我的管理器
     *
     * @return
     */
    @Bean(name = "rememberMeManager")
    public CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        //这个地方有点坑，不是所有的base64编码都可以用，长度过大过小都不行，没搞明白，官网给出的要么0x开头十六进制，要么base64
        cookieRememberMeManager.setCipherKey(Base64.decode("4AvVhmFLUs0KTA3Kprsdag=="));
        return cookieRememberMeManager;
    }

    /**
     * cookie管理
     * rememberMe 需要和jsp中的对应一致
     *
     * @return
     */
    @Bean
    public SimpleCookie rememberMeCookie() {
        SimpleCookie cookie = new SimpleCookie("rememberMe");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(1 * 60 * 60);
        return cookie;
    }
}
