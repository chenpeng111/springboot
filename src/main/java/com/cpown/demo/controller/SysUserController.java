package com.cpown.demo.controller;

import com.cpown.demo.dto.SysUserDto;
import com.cpown.demo.pojo.SysUser;
import com.cpown.demo.service.DepartmentService;
import com.cpown.demo.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户管理controller
 */
@Controller
@RequestMapping("/user")
@Slf4j
public class SysUserController {
    @Resource
    private SysUserService sysUserService;
    @Resource
    private DepartmentService departmentService;

    /**
     * 跳转用户列表
     * @param model
     * @return
     */
    @RequestMapping("/list")
    public String goToList(Model model){
        List<SysUserDto> allUser = sysUserService.getAllUser();
        log.info("查询用户列表：数量={}",allUser.size());
        model.addAttribute("users",allUser);
        return "list";
    }


    /**
     * 跳轉添加用戶頁面
     * @return
     */
    @RequestMapping("/toAdd")
    public String  goToAdd(Model model){
        model.addAttribute("departments",departmentService.getAllDepartment());
        return "adduser";
    }

    /**
     * 保存用戶
     * @param user
     * @return
     */
    @RequestMapping("/saveUser")
    public String saveUser(SysUser user){
        log.info(user.toString());
        try {
            sysUserService.insertUser(user);
        } catch (Exception e) {
        }
        return "redirect:/user/list";
    }

}
