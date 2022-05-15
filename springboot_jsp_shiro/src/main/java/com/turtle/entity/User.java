package com.turtle.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 对应的user表实体对象
 * @author Administrator
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    private String  id;
    private String username;
    private String password;
    private String salt;

    /**
     * 角色信息
     */
    private List<Role> roles;
}
