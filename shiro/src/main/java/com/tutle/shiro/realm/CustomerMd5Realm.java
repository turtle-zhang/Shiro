package com.tutle.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * 自定义使用md5的realm
 */
public class CustomerMd5Realm extends AuthorizingRealm {

    // 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        // 设置角色
        //simpleAuthorizationInfo.addRole();
        simpleAuthorizationInfo.addRoles(Arrays.asList("admin", "player"));

        // 设置操作权限
        //simpleAuthorizationInfo.addStringPermission();
        simpleAuthorizationInfo.addStringPermissions(Arrays.asList("user:*:01", "product.*"));


        return simpleAuthorizationInfo;
    }

    // 认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String principal = (String) authenticationToken.getPrincipal();
        String userName = "zhangsan";
        // 加盐和散列后的密码
        String password = "e4f9bf3e0c58f045e62c23c533fcf633";
        if (userName.equals(principal)) {
            // 指定认证的盐值 ByteSource.Util.bytes("X0*7ps".getBytes())
            return new SimpleAuthenticationInfo(userName, password, ByteSource.Util.bytes("X0*7ps".getBytes()), this.getName());
        }
        return null;
    }
}
