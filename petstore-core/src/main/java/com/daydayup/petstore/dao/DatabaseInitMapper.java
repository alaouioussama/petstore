package com.daydayup.petstore.dao;

import org.apache.ibatis.annotations.Update;

public interface DatabaseInitMapper {

    @Update("CREATE TABLE user (" + //
            "      name VARCHAR(64) NOT NULL PRIMARY KEY," + //
            "      password VARCHAR(128)," + //
            "      createTime DATETIME DEFAULT NOW()" + //
            ")")
    void createUserTable();
}
