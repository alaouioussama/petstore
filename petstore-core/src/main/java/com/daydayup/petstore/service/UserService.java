package com.daydayup.petstore.service;

import java.util.Date;

import com.daydayup.petstore.dao.UserMapper;
import com.daydayup.petstore.entity.User;

public class UserService {

    private UserMapper dao;

    public UserMapper getDao() {
        return dao;
    }

    public void setDao(UserMapper dao) {
        this.dao = dao;
    }

    public void addUser(User user) {
        if (user.getCreateTime() == null) {
            user.setCreateTime(new Date());
        }
        
        dao.addUser(user);
    }
    
    public User findUserByName(String name) {
        return dao.findUserByName(name);
    }
}
