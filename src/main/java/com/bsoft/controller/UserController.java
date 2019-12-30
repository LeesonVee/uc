package com.bsoft.controller;/**
 * Arthur Administrator
 * date 2019/12/26.
 */

import com.alibaba.fastjson.JSON;
import com.bsoft.entity.BaseUser;
import com.bsoft.entity.TestUser;
import com.bsoft.entity.UserOrganization;
import com.bsoft.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *@ClassName UserController
 *@Description TODO
 *@Author Administrator
 *@Date 2019/12/26 15:40
 *@Version 1.0
 **/
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/oracle")
    String index1() {
        TestUser testUser = new TestUser("Hello Oracle");
        userService.addTestUser(testUser);
        return "Hello Oracle";
    }
    @RequestMapping("/oracle2")
    String index2() {
        UserOrganization organ = new UserOrganization();
        organ.setOrganizcode("456");
        organ.setLogoff("2");
        userService.addUserOrganization(organ);
        return "Hello Oracle";
    }

    @RequestMapping("/queryAll.json")
    String index3() {
        List<BaseUser> baseUser = userService.findAll();

        return JSON.toJSONString(baseUser);
    }
}
