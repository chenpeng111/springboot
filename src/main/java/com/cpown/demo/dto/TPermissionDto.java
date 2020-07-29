package com.cpown.demo.dto;

import com.cpown.demo.pojo.TPermission;

/**
 * 权限查询Dto
 */
public class TPermissionDto extends TPermission {
    private String rolename;

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }
}
