package com.turtle.realm;

import com.turtle.entity.Perms;
import com.turtle.entity.User;
import com.turtle.service.impl.UserServiceImpl;
import com.turtle.utils.CustomerByteSource;
import lombok.SneakyThrows;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 自定义realm:如果不加入spring管理则需要自己写个工厂类记性构建，具体看情况
 *
 * @author zhanghh
 */
//@Component  可以加也可以不加
public class CustomerRealm extends AuthorizingRealm {

    @Autowired
    private UserServiceImpl userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // 用户名
        String principal = principals.getPrimaryPrincipal().toString();
        User user = userService.findRolesByUserName(principal);
        if (user != null) {
            SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
            user.getRoles().forEach(role -> {
                // 1.赋予角色
                simpleAuthorizationInfo.addRole(role.getName());

                List<Perms> perms = userService.findPermsByRoleId(role.getId());
                // 会出现有角色，但是没有对应权限的情况，需要特殊判空处理
                if (!CollectionUtils.isEmpty(perms) && perms.get(0)!=null) {
                    // 2.赋予权限
                    perms.forEach(perms1 -> {
                        simpleAuthorizationInfo.addStringPermission(perms1.getName());
                    });
                }
            });
            return simpleAuthorizationInfo;
        }
        return null;
    }

    @SneakyThrows
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) {
        String principal = token.getPrincipal().toString();
        // 使用共厂类或者使用spring注入均可
        //UserService userService = (UserService)ApplicationContextUtil.getBean("userService");
        User user = userService.findByUserName(principal);
        if (user.getUsername().equals(principal)) {
            // 在序列化的时候，shiro提供的ByteSource没有实现序列化接口，所以需要自定义ByteSource来实现序列化接口
            // return new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), ByteSource.Util.bytes(user.getSalt()), this.getName());
            return new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), CustomerByteSource.Util.bytes(user.getSalt()), this.getName());
        }
        return null;
    }

}
