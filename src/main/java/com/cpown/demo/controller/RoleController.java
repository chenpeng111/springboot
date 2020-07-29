package com.cpown.demo.controller;

import com.cpown.demo.pojo.Department;
import com.cpown.demo.pojo.TRole;
import com.cpown.demo.service.TRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * 角色管理controller
 */
@Controller
@RequestMapping("/role")
@Slf4j
public class RoleController {
    @Resource
    private TRoleService roleService;

    /**
     * 角色列表
     * @return
     */
    @RequestMapping("/list")
    public String goToList(Model model){
        List<TRole> allDepartment = roleService.selectAll();
        model.addAttribute("roles",allDepartment);
        return "role/rolelist";
    }


    /**
     * 跳轉添加角色頁面
     * @return
     */
    @RequestMapping("/toAdd")
    public String goToAdd(){
        return "role/addRole";
    }
    /**
     * 跳轉修改角色頁面
     * @return
     */
    @RequestMapping("/toUpdate")
    public String  toUpdate(@RequestParam(value = "id", defaultValue = "")String id, Model model){
        model.addAttribute("role",roleService.selectByPrimaryKey(Integer.valueOf(id)));
        return "role/addRole";
    }
    /**
     * 保存角色
     * @param role
     * @return
     */
    @RequestMapping("/saveRole")
    public String saveUser(TRole role, Model model){
        try {
            if(role.getId() == null ){
                roleService.insert(role);
            }else{
                roleService.updateByPrimaryKey(role);
            }
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            //此处可以向页面传递 错误信息
            model.addAttribute("errormsg",e.getMessage());
        }
        return "redirect:/role/list";
    }

    /**
     * 删除用户
     * @return
     */
    @RequestMapping("/delete")
    public String  delete(String id){
        log.info("删除角色 departmentId={}",id);
        if(!StringUtils.isEmpty(id)){
            roleService.deleteByPrimaryKey(Integer.valueOf(id));
        }
        return "redirect:/role/list";
    }
}
