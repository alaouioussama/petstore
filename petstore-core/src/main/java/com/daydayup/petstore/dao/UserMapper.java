package com.daydayup.petstore.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.daydayup.petstore.entity.User;

public interface UserMapper {

    @Select("SELECT name, password, createTime from user where name = #{name}")
    User findUserByName(@Param("name") String name);
    
    @Select("SELECT name, '******' password, createTime from user")
    List<User> findList();
    
    @Select("SELECT name, '******' password, createTime from user where name like '${x}'")
    List<User> findListX(@Param("x") String x);

    User getAuthenticatedUser(String userId, String password);

    List<String> getUserIdList();

    @Insert("insert into user (name, password, createTime) " + //
            "values (#{user.name}, #{user.password}, #{user.createTime})" //
    )
    void addUser(@Param("user") User user);

    void updateUser(User user);
}
