package com.cpown.demo.config;

import com.cpown.demo.mapper.SysUserMapper;
import com.cpown.demo.pojo.SysUser;
import com.cpown.demo.pojo.TPermission;
import com.cpown.demo.pojo.TRole;
import com.cpown.demo.service.SysUserService;
import com.cpown.demo.service.TPermissionService;
import com.cpown.demo.service.TRoleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 自定义Realm文件 需要继承 AuthorizingRealm 并重写方法
 * create by c-pown on 2020-07-28
 */
@Slf4j
public class MyRealm extends AuthorizingRealm {
    @Resource
    private SysUserService sysUserService;
    @Resource
    TRoleService roleService;
    @Resource
    TPermissionService permissionService;
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("进入授权");
        // 获取用户名
        String username = (String) principalCollection.getPrimaryPrincipal();
        SysUser sysUser = sysUserService.selectSysUserByName(username);
        TRole tRole = roleService.selectByPrimaryKey(sysUser.getRoleId());
        Set<String> stringSet = new HashSet<>();
        stringSet.add(tRole.getRolename());

        List<TPermission> tPermissions = permissionService.selectByRoleId(tRole.getId());
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(stringSet);
        authorizationInfo.setStringPermissions(tPermissions.stream().map(TPermission::getPermissionname).collect(Collectors.toSet()));

        return authorizationInfo;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.info("进入认证");
        UsernamePasswordToken authentication = (UsernamePasswordToken)authenticationToken;
        SysUser user = sysUserService.selectSysUserByName((String)authentication.getPrincipal());
        if(user != null ){
            SecurityUtils.getSubject().getSession().setAttribute("user",user);
            return  new SimpleAuthenticationInfo(user.getName(),user.getPassword(),"myRealm");
        }
        return null;
    }
}
