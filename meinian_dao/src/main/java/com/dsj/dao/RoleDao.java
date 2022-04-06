package com.dsj.dao;

import com.dsj.pojo.Role;

import java.util.Set;

public interface RoleDao {
   Set<Role> findRolesByUserId(Integer id);
}
