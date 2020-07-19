package com.cpown.demo.service;

import com.cpown.demo.mapper.DepartmentMapper;
import com.cpown.demo.pojo.Department;
import com.cpown.demo.pojo.SysUser;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

@Component
public class DepartmentService {

    @Resource
    DepartmentMapper departmentMapper;


    public Collection<Department> getDepartment(){
        return departmentMapper.selectAll();
    }


    public Department getDepartmentById(Integer id){
        return departmentMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据用户名称查询用户
     * @param name
     * @return
     */
    public List<Department> getDepartmentByName(String name){
        return departmentMapper.selectDepartmentByName(name);
    }

    /**
     * 查询所有用户
     * @return
     */
    public List<Department> getAllDepartment(){
        return departmentMapper.selectAll();
    }

    /**
     * 插入用户
     */
    public void insertDepartment( Department department){
        departmentMapper.insert(department);
    }

    /**
     * 修改用户
     */
    public void updateDepartment( Department department){
        departmentMapper.updateByPrimaryKey(department);
    }

    /**
     * 刪除用户
     */
    public void deleteDepartment(Integer id){
        departmentMapper.deleteByPrimaryKey(id);
    }
}
