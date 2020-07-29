package com.cpown.demo.service;

import com.cpown.demo.mapper.TRoleMapper;
import com.cpown.demo.pojo.TRole;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 角色Service
 */
@Service
public class TRoleService implements TRoleMapper {

    @Resource
    TRoleMapper mapper;


    @Override
    public int deleteByPrimaryKey(Integer id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(TRole record) {
        return mapper.insert(record);
    }

    @Override
    public TRole selectByPrimaryKey(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<TRole> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(TRole record) {
        return mapper.updateByPrimaryKey(record);
    }
}
