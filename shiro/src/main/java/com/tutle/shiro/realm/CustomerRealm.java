package com.tutle.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.PrincipalCollection;

// 系统存在一个默认的SimpleAccountRealm,，但是当我们使用自定义的realm的时候，就需要继承 AuthorizingRealm
// 我们自定义实现的realm和SimpleAccountRealm是同一级的
public class CustomerRealm extends AuthorizingRealm {

    /**
     * 认证方法实现
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 在token中获取用户名
        String principal = (String) token.getPrincipal();
        // 这个需要从数据库中获取数据
        String userName = "zhangsan";
        String password = "123";
        if (userName.equals(principal)) {
            // 用户名认证通过
            AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(userName, password, this.getName());
            return authenticationInfo;
        } else {
            throw new AuthenticationException("账号不符合要求");
        }
    }

    /**
     * 授权方法实现
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }
}
