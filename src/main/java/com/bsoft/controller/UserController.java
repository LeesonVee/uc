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

    @RequestMapping(value = "/logon.json", method = {RequestMethod.GET,RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    Response logon(@RequestBody JSONObject jsonParam) throws ModelOperationException{
        System.out.println(jsonParam);
        if(jsonParam==null || "".equals(jsonParam.get("username")) || "".equals(jsonParam.get("password"))){
            throw new ModelOperationException(ErrorCodeAndMsg.HTTP_CODE_415);
        }
        BaseUser user = userService.checkLogonUser(jsonParam.get("username").toString(),jsonParam.get("password").toString(),"1");
        return new Response(user);
    }
}
