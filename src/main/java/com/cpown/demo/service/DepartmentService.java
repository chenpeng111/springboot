package com.cpown.demo.service;

import com.cpown.demo.mapper.DepartmentMapper;
import com.cpown.demo.pojo.Department;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 部门Service
 */
@Service
public class DepartmentService implements DepartmentMapper{

    @Resource
    DepartmentMapper departmentMapper;


    @Override
    public int deleteByPrimaryKey(Integer id) {
        return departmentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Department record) {
        return departmentMapper.insert(record);
    }

    @Override
    public Department selectByPrimaryKey(Integer id) {
        return departmentMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Department> selectAll() {
        return departmentMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Department record) {
        return departmentMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Department> selectDepartmentByName(String name) {
        return null;
    }
}
