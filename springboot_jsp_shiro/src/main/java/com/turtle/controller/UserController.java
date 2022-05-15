package com.turtle.controller;

import com.turtle.entity.User;
import com.turtle.service.impl.UserServiceImpl;
import com.turtle.utils.VerifyCodeUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author zhanghh
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    /**
     * 登录操作：需要进行对应的账号和密码的校验
     *
     * @return
     */
    @RequestMapping("/login")
    public String login(String username, String password,Boolean rememberMe,String code,HttpSession session) {
        //比较验证码
        String codes = (String) session.getAttribute("code");
        try {
            if (codes.equalsIgnoreCase(code)){
                //获取主体对象
                Subject subject = SecurityUtils.getSubject();
                // 加上记住我的功能
                UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password,rememberMe);
                subject.login(usernamePasswordToken);
                return "redirect:/index.jsp";
            }else{
                throw new RuntimeException("验证码错误!");
            }
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
     *
     * @return
     */
    @RequestMapping("/logout")
    public String logout() {
        // 登出操作
        SecurityUtils.getSubject().logout();
        return "redirect:/login.jsp";
    }

    /**
     * 注册
     * @param user
     * @return
     */
    @RequestMapping("/register")
    public String register(User user) {
        try {
            userServiceImpl.register(user);
            return "redirect:/login.jsp";
        } catch (Exception e) {
            return "redirect:/register.jsp";
        }
    }

    @RequestMapping("getImage")
    public void getImage(HttpSession session, HttpServletResponse response) throws IOException {
        //生成验证码
        String code = VerifyCodeUtil.generateVerifyCode(4);
        //验证码放入session
        session.setAttribute("code",code);
        //验证码存入图片
        ServletOutputStream os = response.getOutputStream();
        response.setContentType("image/png");
        VerifyCodeUtil.outputImage(220,60,os,code);
    }

}
