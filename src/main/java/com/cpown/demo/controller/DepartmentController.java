package com.cpown.demo.controller;

import com.cpown.demo.pojo.Department;
import com.cpown.demo.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * 部门管理controller
 */
@Controller
@RequestMapping("/depart")
@Slf4j
public class DepartmentController {
    @Resource
    private DepartmentService departmentService;

    /**
     * 部门列表页面
     * 使用 ModelAndView 和 直接 retern String 都可以跳转到页面
     * 如果使用@RestController 则必须使用 ModelAndView跳转页面
     * @return
     */
    @RequestMapping("/list")
    public ModelAndView goToList(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("department/departlist");
        List<Department> allDepartment = departmentService.getAllDepartment();
        log.info("查询部门列表：数量={}",allDepartment.size());
        modelAndView.addObject("departments",allDepartment);
        return modelAndView;
    }


    /**
     * 跳轉添加部门頁面
     * @return
     */
    @RequestMapping("/toAdd")
    public ModelAndView goToAdd(){
        return new ModelAndView("department/addDepartment");
    }
    /**
     * 跳轉修改部门頁面
     * @return
     */
    @RequestMapping("/toUpdate")
    public String  toUpdate(@RequestParam(value = "id", defaultValue = "")String id, Model model){
        model.addAttribute("depart",departmentService.getDepartmentById(Integer.valueOf(id)));
        return "department/addDepartment";
    }
    /**
     * 保存部门
     * @param department
     * @return
     */
    @RequestMapping("/saveDepartment")
    public String saveUser(Department department, Model model){
        try {
            if(department.getId() == null ){
                departmentService.insertDepartment(department);
            }else{
                departmentService.updateDepartment(department);
            }
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            //此处可以向页面传递 错误信息
            model.addAttribute("errormsg",e.getMessage());
        }
        return "redirect:/depart/list";
    }

    /**
     * 删除用户
     * @return
     */
    @RequestMapping("/delete")
    public String  delete(String id){
        log.info("删除部门 departmentId={}",id);
        if(!StringUtils.isEmpty(id)){
            departmentService.deleteDepartment(Integer.valueOf(id));
        }
        return "redirect:/depart/list";
    }
}
