package com.dsj.service;

import com.dsj.pojo.User;

public interface UserService {
    User findUserByUsername(String username);
}
