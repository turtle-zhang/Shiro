package com.tutle.shiro;


import com.tutle.shiro.realm.CustomerRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;

/**
 * 测试自定义的realm
 */
public class CustomerAuthenticationTest {
    public static void main(String[] args) {
        // 1 创建安全管理器对象
        DefaultSecurityManager securityManager = new DefaultSecurityManager();

        // 2 给安全管理器添加我们自定义的realm
        securityManager.setRealm(new CustomerRealm());

        // 3 给给全局安全工具SecurityManager
        SecurityUtils.setSecurityManager(securityManager);

        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("zhangsan", "123");

        try {
            System.out.println("登录状态：" + subject.isAuthenticated());
            subject.login(usernamePasswordToken);
            System.out.println("登录状态：" + subject.isAuthenticated());
        } catch (UnknownAccountException e) {
            System.out.println("账号异常！");
        } catch (IncorrectCredentialsException e) {
            System.out.println("密码异常！");
        }


    }
}
