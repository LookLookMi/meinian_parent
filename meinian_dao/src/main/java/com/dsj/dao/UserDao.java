package com.dsj.dao;

import com.dsj.pojo.User;

public interface UserDao {
    User findUserByUsername(String username);

}
