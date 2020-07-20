package com.cpown.demo.service;

import com.cpown.demo.dto.SysUserDto;
import com.cpown.demo.mapper.DepartmentMapper;
import com.cpown.demo.mapper.SysUserMapper;
import com.cpown.demo.pojo.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户Service
 */
@Service
public class SysUserService {

    @Resource
    SysUserMapper sysUserMapper;


    /**
     * 根据主键查询
     * @param id
     * @return
     */
    public SysUser getDepartmentById(Integer id){
        return sysUserMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据用户名称查询用户
     * @param name
     * @return
     */
    public List<SysUser> getSysUserByName(String name){
        return sysUserMapper.selectSysUserByName(name);
    }

    /**
     * 查询所有用户
     * @return
     */
    public List<SysUserDto> getAllUser(){
        return sysUserMapper.selectAll();
    }

    /**
     * 插入用户
     */
    public void insertUser(SysUser sysUser){
         sysUserMapper.insert(sysUser);
    }

    /**
     * 修改用户
     */
    public void updateUser(SysUser sysUser){
        sysUserMapper.updateByPrimaryKey(sysUser);
    }

    /**
     * 刪除用户
     */
    public void deleteUser(Integer id){
        sysUserMapper.deleteByPrimaryKey(id);
    }
}
