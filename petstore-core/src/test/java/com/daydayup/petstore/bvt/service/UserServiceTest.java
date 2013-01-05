package com.daydayup.petstore.bvt.service;

import org.junit.Assert;

import com.daydayup.petstore.BuildVerifyTest;
import com.daydayup.petstore.entity.User;
import com.daydayup.petstore.service.AccessLogService;
import com.daydayup.petstore.service.UserService;

public class UserServiceTest extends BuildVerifyTest {

    public void test_addUser() throws Exception {
        UserService userService = getBean(UserService.class);

        User user = new User();
        user.setName("admin");
        user.setPassword("admin");
        userService.addUser(user);
        
        User loadedUser = userService.findUserByName("admin");
        Assert.assertNotNull(loadedUser);
        
        {
        	AccessLogService logService = getBean(AccessLogService.class);
        	logService.add("", "", "", "");
        }
    }
}
