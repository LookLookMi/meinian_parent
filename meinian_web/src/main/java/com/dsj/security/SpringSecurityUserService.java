package com.dsj.security;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dsj.pojo.Permission;
import com.dsj.pojo.Role;
import com.dsj.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class SpringSecurityUserService implements UserDetailsService {

    @Reference
    UserService userService; //远程调用

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //1.查询用户信息，以及用户对应角色，以及角色对应权限
        com.dsj.pojo.User user = userService.findUserByUsername(username);

        if(user==null){ //不存在这个用户
            return null ; //返回null给框架，框架会抛异常。进行异常处理，跳转到登录页面
        }

        //2.构建权限集合
        Set<GrantedAuthority> authorities = new HashSet<>();
        Set<Role> roles = user.getRoles(); //集合数据 由  RoleDao帮忙方法来查询得到的。
        for (Role role : roles) {
            Set<Permission> permissions = role.getPermissions(); //集合数据 由 PermissionDao帮忙方法来查询得到的。
            for (Permission permission : permissions) {
                authorities.add(new SimpleGrantedAuthority(permission.getKeyword()));
            }
        }
        org.springframework.security.core.userdetails.User sucurityUser =
                new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authorities);
        return sucurityUser; //框架提供User实现了UserDetails接口
    }
}
