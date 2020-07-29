package com.cpown.demo.controller;

import com.cpown.demo.dto.SysUserDto;
import com.cpown.demo.pojo.SysUser;
import com.cpown.demo.service.DepartmentService;
import com.cpown.demo.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

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
        return "user/list";
    }


    /**
     * 跳轉添加用戶頁面
     * @return
     */
    @RequestMapping("/toAdd")
    public String  goToAdd(Model model){
        log.info("跳转用户编辑界面");
        model.addAttribute("departments",departmentService.getAllDepartment());
        return "user/adduser";
    }

    /**
     * 跳轉修改用戶頁面
     * @return
     */
    @RequestMapping("/toUpdate")
    public String  toUpdate(@RequestParam(value = "id", defaultValue = "")String id, Model model){
        log.info("跳转用户编辑界面");
        model.addAttribute("departments",departmentService.getAllDepartment());
        if(!StringUtils.isEmpty(id)){
            model.addAttribute("user",sysUserService.getDepartmentById(Integer.valueOf(id)));
        }
        return "user/adduser";
    }


    /**
     * 保存用戶
     * @param user
     * @return
     */
    @RequestMapping("/saveUser")
    public String saveUser(SysUser user){
        try {
            if(user.getId() == null){
                log.info("添加用户user={}",user.toString());
                sysUserService.insertUser(user);
            }else{
                log.info("修改用户user={}",user.toString());
                sysUserService.updateUser(user);
            }
        } catch (Exception e) {
        }
        return "redirect:/user/list";
    }
    /**
     * 删除用户
     * @return
     */
    @RequestMapping("/delete")
    public String  delete(String id){
        log.info("删除用户 userid={}",id);
        if(!StringUtils.isEmpty(id)){
            sysUserService.deleteUser(Integer.valueOf(id));
        }
        return "redirect:/user/list";
    }

}
