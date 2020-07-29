package com.cpown.demo.controller;

import com.cpown.demo.dto.TPermissionDto;
import com.cpown.demo.pojo.TPermission;
import com.cpown.demo.pojo.TRole;
import com.cpown.demo.service.TPermissionService;
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
 * 权限管理controller
 */
@Controller
@RequestMapping("/perm")
@Slf4j
public class PermController {
    @Resource
    private TPermissionService permissionService;
    @Resource
    private TRoleService tRoleService;

    /**
     * 角色列表
     * @return
     */
    @RequestMapping("/list")
    public String goToList(Model model){
        List<TPermissionDto> allDepartment = permissionService.selectAllDto();
        model.addAttribute("perms",allDepartment);
        return "perm/permlist";
    }


    /**
     * 跳轉添加角色頁面
     * @return
     */
    @RequestMapping("/toAdd")
    public String goToAdd(Model model){
        model.addAttribute("roles",tRoleService.selectAll());
        return "perm/addPerm";
    }
    /**
     * 跳轉修改角色頁面
     * @return
     */
    @RequestMapping("/toUpdate")
    public String  toUpdate(@RequestParam(value = "id", defaultValue = "")String id, Model model){
        model.addAttribute("roles",tRoleService.selectAll());
        model.addAttribute("perm",permissionService.selectByPrimaryKey(Integer.valueOf(id)));
        return "perm/addPerm";
    }
    /**
     * 保存角色
     * @param permission
     * @return
     */
    @RequestMapping("/savePerm")
    public String saveUser(TPermission permission, Model model){
        try {
            if(permission.getId() == null ){
                permissionService.insert(permission);
            }else{
                permissionService.updateByPrimaryKey(permission);
            }
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            //此处可以向页面传递 错误信息
            model.addAttribute("errormsg",e.getMessage());
        }
        return "redirect:/perm/list";
    }

    /**
     * 删除用户
     * @return
     */
    @RequestMapping("/delete")
    public String  delete(String id){
        log.info("删除角色 departmentId={}",id);
        if(!StringUtils.isEmpty(id)){
            permissionService.deleteByPrimaryKey(Integer.valueOf(id));
        }
        return "redirect:/perm/list";
    }
}
