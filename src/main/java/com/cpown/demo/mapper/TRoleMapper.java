package com.cpown.demo.mapper;

import com.cpown.demo.pojo.TRole;
import java.util.List;

public interface TRoleMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(TRole record);

    TRole selectByPrimaryKey(Integer id);

    List<TRole> selectAll();

    int updateByPrimaryKey(TRole record);
}