package com.cpown.demo.config;

import com.cpown.demo.mapper.SysUserMapper;
import com.cpown.demo.pojo.SysUser;
import com.cpown.demo.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * 自定义Realm文件 需要继承 AuthorizingRealm 并重写方法
 * create by c-pown on 2020-07-28
 */
@Slf4j
public class MyRealm extends AuthorizingRealm {
    @Resource
    private SysUserService sysUserService;
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("进入授权");
        return null;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.info("进入认证");
        UsernamePasswordToken authentication = (UsernamePasswordToken)authenticationToken;
        List<SysUser> users = sysUserService.selectSysUserByName(authentication.getUsername());
        if(!CollectionUtils.isEmpty(users)){
            return  new SimpleAuthenticationInfo("",users.get(0).getPassword(),"");
        }
        return null;
    }
}
