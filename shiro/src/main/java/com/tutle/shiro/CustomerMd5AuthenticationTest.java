package com.tutle.shiro;

import com.tutle.shiro.realm.CustomerMd5Realm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;

import java.util.Arrays;

/**
 * 测试自定义的realm-使用md5的realm
 */
public class CustomerMd5AuthenticationTest {

    public static void main(String[] args) {
        // 1 创建安全管理器对象
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        //2.注入realm
        CustomerMd5Realm realm = new CustomerMd5Realm();

        //3.创建使用hash凭证匹配器
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        //声明：使用的算法
        credentialsMatcher.setHashAlgorithmName("md5");
        //声明：散列次数
        credentialsMatcher.setHashIterations(1024);

        // 将密码器设置到realm中
        realm.setCredentialsMatcher(credentialsMatcher);
        // 盐值的设置在认证里面已经进行了全局的设置

        // 2 给安全管理器添加我们自定义的realm
        securityManager.setRealm(realm);

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

        // 当认证通过之后就需要进行授权的认证了
        if (subject.isAuthenticated()) {
            // 是否具有admin的角色权限
            boolean adminAuthor = subject.hasRole("admin");

            // 是否觉有其中的一个权限
            boolean[] hasRoles = subject.hasRoles(Arrays.asList("admin", "test"));

            // 需要包含显示的所有的权限
            boolean hasAllRoles = subject.hasAllRoles(Arrays.asList("admin", "test"));

            // 是否具有其中的一个权限
            boolean permitted = subject.isPermitted("user:*:01");

            // 是否具有其中的一项权限
            boolean[] permitted1 = subject.isPermitted("user:*:01", "product.*");

            // 需要具有所有的权限
            boolean permittedAll = subject.isPermittedAll("user:*:01", "product.*");

            System.out.println(adminAuthor);
        }

    }
}
