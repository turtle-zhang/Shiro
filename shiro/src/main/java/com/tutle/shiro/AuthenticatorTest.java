package com.tutle.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;

/**
 * 测试shiro的认证功能 1-1
 */
public class AuthenticatorTest {
    public static void main(String[] args) {

        // 1.创建安全管理器对象
        DefaultSecurityManager securityManager = new DefaultSecurityManager();

        // 2. 给安全管理器设置realm
        // D:\Project\Java\Idea-2021\shiro\shiro_1\src\test\resources\shiroConfig.ini
        securityManager.setRealm(new IniRealm("classpath:shiroConfig.ini"));

        // 3.SecurityUtils 给全局安全工具SecurityManager
        SecurityUtils.setSecurityManager(securityManager);

        Subject subject = SecurityUtils.getSubject();

        // 4. 设置用户令牌信息
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("hangman", "1231");

        // 如果认证成功不会返回任何信息，但是认证失败会报对应的异常信息
        try {
            System.out.println("认证状态：" + subject.isAuthenticated());
            subject.login(usernamePasswordToken);
            System.out.println("认证状态：" + subject.isAuthenticated());
        } catch (UnknownAccountException e) {
            System.out.println("账号异常！");
        } catch (IncorrectCredentialsException e) {
            System.out.println("密码异常！");
        }
    }

}
