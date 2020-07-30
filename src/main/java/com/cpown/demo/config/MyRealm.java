package com.cpown.demo.config;

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

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 自定义Realm文件 需要继承 AuthorizingRealm 并重写认证，授权方法
 * create by c-pown on 2020-07-28
 */
@Slf4j
public class MyRealm extends AuthorizingRealm {
    @Resource
    private SysUserService sysUserService;
    /**
     * 角色service
     */
    @Resource
    TRoleService roleService;
    /**
     * 权限service
     */
    @Resource
    TPermissionService permissionService;
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("进入授权");
        // 获取用户名
        String username = (String) principalCollection.getPrimaryPrincipal();
        //查询当前用户
        SysUser sysUser = sysUserService.selectSysUserByName(username);
        //获取当前用户角色
        TRole tRole = roleService.selectByPrimaryKey(sysUser.getRoleId());
        //将角色信息放进Set
        Set<String> stringSet = new HashSet<>();
        stringSet.add(tRole.getRolename());
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //将当前用户角色信息，交给授权管理器处理
        authorizationInfo.setRoles(stringSet);
        //通过当前角色信息  获取权限信息
        List<TPermission> tPermissions = permissionService.selectByRoleId(tRole.getId());
        //权限信息交给 授权管理器处理
        authorizationInfo.setStringPermissions(tPermissions.stream().map(TPermission::getPermissionname).collect(Collectors.toSet()));
        return authorizationInfo;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.info("进入认证");
        //在登录接口设置
        UsernamePasswordToken authentication = (UsernamePasswordToken)authenticationToken;
        //查询判断当前用户是否存在  不存在则返回null  ===》 登录端会直接抛出UnknownAccountException 异常
        SysUser user = sysUserService.selectSysUserByName((String)authentication.getPrincipal());
        if(user != null ){
            //将当前用户信息放进session
            SecurityUtils.getSubject().getSession().setAttribute("user",user);
            //如果用户存在 则将用户密码 交给认证管理器 处理，如果错误的话====》登录端会直接抛出IncorrectCredentialsException异常
            return  new SimpleAuthenticationInfo(user.getName(),user.getPassword(),"myRealm");
        }
        return null;
    }
}
