package com.cpown.demo.controller;

import com.cpown.demo.pojo.Department;
import com.cpown.demo.pojo.SysUser;
import com.cpown.demo.service.DepartmentService;
import com.cpown.demo.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.StringUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户管理controller
 */
@Controller
@RequestMapping("/depart")
@Slf4j
public class DepartmentController {
    @Resource
    private DepartmentService departmentService;

    @RequestMapping("/list")
    public ModelAndView goToList(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("departlist");
        List<Department> allDepartment = departmentService.getAllDepartment();
        log.info("查询部门列表：数量={}",allDepartment.size());
        modelAndView.addObject("departments",allDepartment);
        return modelAndView;
    }


    /**
     * 跳轉添加用戶頁面
     * @return
     */
    @RequestMapping("/toAdd")
    public ModelAndView goToAdd(){
        return new ModelAndView("addDepartment");
    }

    /**
     * 保存用戶
     * @param department
     * @return
     */
    @RequestMapping("/saveDepartment")
    public String saveUser(Department department){
        log.info(department.toString());
        ModelAndView modelAndView = new ModelAndView();
        Map<String,Object> resultMap = new HashMap<>();
        try {
            departmentService.insertDepartment(department);
            resultMap.put("code","0000");
            resultMap.put("msg","保存成功");
        } catch (Exception e) {
            resultMap.put("code","9999");
            resultMap.put("msg",e.getMessage());
        }
        modelAndView.setViewName("departlist");
        return "redirect:/depart/list";
    }

}
