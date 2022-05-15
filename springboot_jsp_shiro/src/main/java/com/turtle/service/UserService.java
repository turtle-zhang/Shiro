package com.turtle.service;

import com.turtle.entity.Perms;
import com.turtle.entity.User;

import java.util.List;

/**
 * @author Administrator
 */
public interface UserService {
    /**
     * 注册用户方法
     * @param user
     */
    void register(User user) throws Exception;

    /**
     * 根据用户名查询业务的方法
     * @param username
     * @return
     */
    User findByUserName(String username) throws Exception;

    /**
     * 根据用户名查询所有角色
     * @param username
     * @return
     */
    User findRolesByUserName(String username);

    /**
     * 根据角色id查询权限集合
     * @param id
     * @return
     */
    List<Perms> findPermsByRoleId(String id);


}
