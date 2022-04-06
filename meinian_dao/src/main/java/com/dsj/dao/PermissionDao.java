package com.dsj.dao;

import com.dsj.pojo.Permission;

import java.util.Set;

public interface PermissionDao {
    Set<Permission> findPermissionsByRoleId(Integer id);
}
