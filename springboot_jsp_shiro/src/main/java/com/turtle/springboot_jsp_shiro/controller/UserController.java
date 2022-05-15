package com.turtle.springboot_jsp_shiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    /**
     * 登录操作：需要进行对应的账号和密码的校验
     *
     * @return
     */
    @RequestMapping("/login")
    public String login(String username, String password) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
        try {
            subject.login(usernamePasswordToken);
            // 登录成功
            return "redirect:/index.jsp";
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            System.out.println("账号错误");
        } catch (IncorrectCredentialsException e) {
            e.printStackTrace();
            System.out.println("密码错误");
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 登录失败
        return "redirect:/login.jsp";
    }

    /**
     * 退出登录操作
     * @return
     */
    @RequestMapping("/logout")
    public String logout(){
        // 登出操作
        SecurityUtils.getSubject().logout();
        return "redirect:/login.jsp";
    }
}
