package com.turtle.service.impl;

import com.turtle.dao.UserDao;
import com.turtle.entity.Perms;
import com.turtle.entity.User;
import com.turtle.service.UserService;
import com.turtle.utils.SaltUtil;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author zhanghaihui
 * @Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)：将所有方法上的事务都强制改为是CGLib方式代理的
 */
@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserServiceImpl implements UserService {

    /**
     * 设置取8位随机盐
     */
    private static final Integer SALT_LENGTH = 8;

    @Autowired
    private UserDao userDao;

    /**
     * 注册
     * @param user
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void register(User user) throws Exception {
        if (user == null){
            throw new Exception("用户为空，存在异常！");
        }
        String password = user.getPassword();
        String salt = SaltUtil.getSalt(SALT_LENGTH);
        // 需要对密码做加密处理 加盐 +  1024次随机散列
        Md5Hash md5Hash = new Md5Hash(password,salt,1024);
        user.setPassword(md5Hash.toHex());
        user.setSalt(salt);
        userDao.save(user);
    }


    @Override
    public User findByUserName(String username) throws Exception {
        User user = userDao.findByUserName(username);
        if (user == null){
            throw new Exception("用户为空，存在异常！");
        }
        return user;
    }

    @Override
    public User findRolesByUserName(String username) {
        return userDao.findRolesByUserName(username);
    }

    @Override
    public List<Perms> findPermsByRoleId(String id) {
        return userDao.findPermsByRoleId(id);
    }
}
