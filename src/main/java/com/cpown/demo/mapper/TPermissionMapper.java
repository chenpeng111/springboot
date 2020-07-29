package com.cpown.demo.mapper;

import com.cpown.demo.dto.TPermissionDto;
import com.cpown.demo.pojo.TPermission;
import java.util.List;

public interface TPermissionMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(TPermission record);

    TPermission selectByPrimaryKey(Integer id);

    List<TPermission> selectAll();

    int updateByPrimaryKey(TPermission record);

    List<TPermissionDto> selectAllDto();

    List<TPermission> selectByRoleId(Integer roleId);


}