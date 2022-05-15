package com.turtle.dao;

import com.turtle.entity.Perms;
import com.turtle.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author zhanghaihui
 */
@Mapper
public interface UserDao {

    /**
     * 保存用户信息
     * @param user
     */
    void save(User user);

    /**
     * 根据身份信息认证的方法
     * @param username
     * @return
     */
    User findByUserName(String username);

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
