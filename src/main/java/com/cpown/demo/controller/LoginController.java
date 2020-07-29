package com.cpown.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * 用户登录controller
 *
 */
@Controller
@RequestMapping("/sys")
@Slf4j
public class LoginController {

    @RequestMapping("/login")
    public String login(@RequestParam("userName")String username, @RequestParam("password") String password, Model model){
        log.info("用户登录：username={}",username);
        //获取到 Subject 对象
        Subject subject = SecurityUtils.getSubject();
        //将当前 登录用户 放进Subject
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);
        } catch (UnknownAccountException e) {//用户不存在
            model.addAttribute("msg","账号不存在");
            return "index";
        }catch (IncorrectCredentialsException e1){
            model.addAttribute("msg","密码错误");
            return "index";
        }
        return "dashboard";
    }
    @RequestMapping("/toLogin")
    public String toLogin(Model model){
        return "index";
    }
    /**
     * 首页
     * @return
     */
    @RequestMapping("/dashboard")
    public ModelAndView toDashBoard(){
        return  new ModelAndView("dashboard");
    }


}
