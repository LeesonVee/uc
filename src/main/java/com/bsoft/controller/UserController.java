package com.bsoft.controller;/**
 * Arthur Administrator
 * date 2019/12/26.
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bsoft.entity.BaseUser;
import com.bsoft.entity.TestUser;
import com.bsoft.entity.UserOrganization;
import com.bsoft.message.ErrorCodeAndMsg;
import com.bsoft.message.ModelOperationException;
import com.bsoft.message.Response;
import com.bsoft.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *@ClassName UserController
 *@Description TODO
 *@Author Vee
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
    @RequestMapping("/getBaseUserByPkey.json")
    String getBaseUserByPkey(){
        String id = "asd";
        boolean flag = userService.existsById(id);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("msg","查询成功");
        map.put("body",flag);
        return JSON.toJSONString(map);
    }
    @RequestMapping(value = "/testException.json", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    Response testException(@RequestBody JSONObject jsonParam) throws ModelOperationException{
        System.out.println(jsonParam);
        throw new ModelOperationException(ErrorCodeAndMsg.HTTP_CODE_500);
    }
}
