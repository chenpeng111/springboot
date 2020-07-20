package com.cpown.demo.service;

import com.cpown.demo.mapper.DepartmentMapper;
import com.cpown.demo.pojo.Department;
import com.cpown.demo.pojo.SysUser;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * 部门Service
 */
@Service
public class DepartmentService {

    @Resource
    DepartmentMapper departmentMapper;


    public Department getDepartmentById(Integer id){
        return departmentMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据用户名称查询部门
     * @param name
     * @return
     */
    public List<Department> getDepartmentByName(String name){
        return departmentMapper.selectDepartmentByName(name);
    }

    /**
     * 查询所有用部门
     * @return
     */
    public List<Department> getAllDepartment(){
        return departmentMapper.selectAll();
    }

    /**
     * 插入部门
     */
    public void insertDepartment( Department department){
        departmentMapper.insert(department);
    }

    /**
     * 修改部门
     */
    public void updateDepartment( Department department){
        departmentMapper.updateByPrimaryKey(department);
    }

    /**
     * 刪除部门
     */
    public void deleteDepartment(Integer id){
        departmentMapper.deleteByPrimaryKey(id);
    }
}
