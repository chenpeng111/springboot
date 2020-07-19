package com.cpown.demo.controller;

import com.cpown.demo.mapper.SysUserMapper;
import com.cpown.demo.pojo.SysUser;
import com.cpown.demo.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.List;

/**
 * 用户登录controller
 *
 */
@RestController
@RequestMapping("/sys")
@Slf4j
public class LoginController {
    @Resource
    private SysUserService sysUserService;

    @RequestMapping("/login")
    public ModelAndView login(@RequestParam("userName")String username, @RequestParam("password") String password, HttpSession session){
        //使用ModelAndView进行视图转发
        //如果是@Controller 直接使用 Model即可  转发视图直接为页面字符串
        ModelAndView modelAndView = new ModelAndView();
        log.info("username:"+username);
        //根据用户名称查询
        List<SysUser> users = sysUserService.getSysUserByName(username);

        //登录名为空  返回登录页并提示 错误信息
        if(CollectionUtils.isEmpty(users)){
            log.info("login faild");
            //使用modelAndView往前端传值的方式：modelAndView.addObject(key,value)
            modelAndView.addObject("msg","用户名不存在");
            modelAndView.setViewName("index");
            //登录名不为空  进入dashboard页面 并设置session
        }else{
            SysUser sysUser = users.get(0);
            //用户名密码都正确
            if(StringUtils.equals(sysUser.getPassword(),password)){
                modelAndView.setViewName("dashboard");
                //将用户放进缓存
                session.setAttribute("loginUser",sysUser);
            }else{
                //使用modelAndView往前端传值得另一种方式：modelAndView.getModel()
                modelAndView.getModel().put("msg","密码错误");
                modelAndView.setViewName("index");
            }
        }
        return modelAndView;
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
