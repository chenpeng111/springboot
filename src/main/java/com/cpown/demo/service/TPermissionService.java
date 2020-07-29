package com.cpown.demo.service;

import com.cpown.demo.dto.TPermissionDto;
import com.cpown.demo.mapper.TPermissionMapper;
import com.cpown.demo.pojo.TPermission;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 权限Service
 */
@Service
public class TPermissionService implements TPermissionMapper {

    @Resource
    TPermissionMapper mapper;


    @Override
    public int deleteByPrimaryKey(Integer id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(TPermission record) {
        return mapper.insert(record);
    }

    @Override
    public TPermission selectByPrimaryKey(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<TPermission> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(TPermission record) {
        return mapper.updateByPrimaryKey(record);
    }

    @Override
    public List<TPermissionDto> selectAllDto() {
        return mapper.selectAllDto();
    }

    @Override
    public List<TPermission> selectByRoleId(Integer roleId) {
        return mapper.selectByRoleId(roleId);
    }


}
