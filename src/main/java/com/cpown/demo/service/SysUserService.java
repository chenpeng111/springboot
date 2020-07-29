package com.cpown.demo.service;

import com.cpown.demo.dto.SysUserDto;
import com.cpown.demo.mapper.SysUserMapper;
import com.cpown.demo.pojo.SysUser;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户Service
 */
@Service
public class SysUserService implements SysUserMapper{

    @Resource
    SysUserMapper sysUserMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return sysUserMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(SysUser record) {
        return sysUserMapper.insert(record);
    }

    @Override
    public SysUser selectByPrimaryKey(Integer id) {
        return sysUserMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SysUserDto> selectAll() {
        return sysUserMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(SysUser record) {
        return sysUserMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<SysUser> selectSysUserByName(String name) {
        return sysUserMapper.selectSysUserByName(name);
    }
}
