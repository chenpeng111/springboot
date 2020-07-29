package com.cpown.demo.dto;

import com.cpown.demo.pojo.SysUser;

/**
 * 用户查询Dto
 */
public class SysUserDto extends SysUser {
    private String departmentName;
    private String rolename;

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
