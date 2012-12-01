package com.daydayup.petstore.service;

import java.security.PrivilegedAction;

import com.alibaba.druid.wall.WallProvider;
import com.daydayup.petstore.dao.DatabaseInitMapper;

public class DatabaseInitService {

    private DatabaseInitMapper dao;

    public DatabaseInitMapper getDao() {
        return dao;
    }

    public void setDao(DatabaseInitMapper dao) {
        this.dao = dao;
    }

    public void createUserTable() {
        WallProvider.doPrivileged(new PrivilegedAction<Object>() {

            @Override
            public Object run() {
                dao.createUserTable();
                return null;
            }
        });
    }
}
