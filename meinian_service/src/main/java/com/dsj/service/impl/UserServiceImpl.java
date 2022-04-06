package com.dsj.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.dsj.dao.UserDao;
import com.dsj.pojo.User;
import com.dsj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * Author    DSJ
 * date:   2022/4/4 16:32
 **/
@Service(interfaceClass = UserService.class)
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;
    @Override
    public User findUserByUsername(String username) {
        return userDao.findUserByUsername(username);
    }
}
