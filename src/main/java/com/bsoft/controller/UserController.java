package com.bsoft.controller;/**
 * Arthur Administrator
 * date 2019/12/26.
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bsoft.entity.*;
import com.bsoft.message.ErrorCodeAndMsg;
import com.bsoft.message.ModelOperationException;
import com.bsoft.message.Response;
import com.bsoft.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

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

    @RequestMapping(value = "/saveSysDomain.json", method = {RequestMethod.GET,RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    Response doSaveSysDomain(@RequestBody JSONObject jsonParam) throws ModelOperationException{
        System.out.println(jsonParam);
        SysDomain domain = new SysDomain();
        if("modify".equals(jsonParam.get("opt"))){
            domain.setId(jsonParam.get("id").toString());
        }
        domain.setDomainCode(jsonParam.get("domainCode").toString());
        domain.setDomainName(jsonParam.get("domainName").toString());
        domain.setRemark(jsonParam.get("remark").toString());
        domain.setStatus(jsonParam.get("status").toString());
        domain.setDomainUrl(jsonParam.get("domainUrl").toString());
        domain.setCreateDate(new Date());
        domain = userService.saveOrUpdateSysDomain(domain);
        return new Response(domain);
    }
    @RequestMapping(value = "/querySysDomain.json", method = {RequestMethod.GET,RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    Response doQuerySysDomain(@RequestBody JSONObject jsonParam) throws ModelOperationException{
        String status = "1";
        if(!jsonParam.isEmpty() && jsonParam.containsKey("status")){
            status = jsonParam.get(status).toString();
        }
        List<SysDomain> list = userService.getSysDomain(status);
        return new Response(list);
    }
    @RequestMapping(value = "/querySysDomainByConditions.json", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    Map<String,Object> doQuerySysDomainByConditions(@RequestBody JSONObject jsonParam) throws ModelOperationException{
        Map<String,Object> map = new HashMap<String,Object>();
        Object status = "1";
        int pageNo = 1;
        int pageSize = 25;
        if(jsonParam.containsKey("status")){
            status = jsonParam.get("status");
        }
        map.put("status",status);
        if(jsonParam.containsKey("id")){
            map.put("id",jsonParam.get("id"));
        }
        if(jsonParam.containsKey("domainCode")){
            map.put("domain_code",jsonParam.get("domainCode"));
        }
        if(jsonParam.containsKey("domainName")){
            map.put("domain_name",jsonParam.get("domainName"));
        }
        if(jsonParam.containsKey("domainUrl")){
            map.put("domain_url",jsonParam.get("domainUrl"));
        }
        if(jsonParam.containsKey("pageNo")){
            pageNo = Integer.parseInt(jsonParam.get("pageNo").toString());
        }
        if(jsonParam.containsKey("pageSize")){
            pageSize = Integer.parseInt(jsonParam.get("pageSize").toString());
        }
        return userService.querySysDomainByCondition(map,pageNo,pageSize);
    }
    @RequestMapping(value = "/queryUserOrganByConditions.json", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String,Object> queryUserOrganByConditions(@RequestBody JSONObject jsonParam) throws ModelOperationException{
        Map<String,Object> map = new HashMap<String,Object>();
        Object logoff = "1";
        int pageNo = 1;
        int pageSize = 25;
        if(jsonParam.containsKey("pageNo")){
            pageNo = Integer.parseInt(jsonParam.get("pageNo").toString());
        }
        if(jsonParam.containsKey("pageSize")){
            pageSize = Integer.parseInt(jsonParam.get("pageSize").toString());
        }
        if(jsonParam.containsKey("logoff")){
            logoff = jsonParam.get("logoff");
        }
        map.put("logoff",logoff);
        return userService.queryUserOrganByCondition(map,pageNo,pageSize);
    }
}
