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
import com.bsoft.utils.PinYinUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;
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

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

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
        if(jsonParam.containsKey("id")){
            map.put("id",jsonParam.get("id"));
        }
        if(jsonParam.containsKey("parentId")){
            map.put("parent_id",jsonParam.get("parentId"));
        }
        if(jsonParam.containsKey("organizCode")){
            map.put("organiz_code",jsonParam.get("organizCode"));
        }
        if(jsonParam.containsKey("organizName")){
            map.put("organiz_name",jsonParam.get("organizName"));
        }
        return userService.queryUserOrganByCondition(map,pageNo,pageSize);
    }
    @RequestMapping(value = "/saveOrganOrOffice.json", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    Response doSaveOrganOrOffice(@RequestBody JSONObject jsonParam) throws ModelOperationException{
        logger.info("进入保存机构方法：请求参数---->jsonParam=【"+jsonParam+"】");
        Map<String,Object> data = (Map<String, Object>) jsonParam.get("data");
        if("organ".equals(jsonParam.get("type"))){
            UserOrganization organization = new UserOrganization();
            if("modify".equals(jsonParam.get("opt"))){
                organization.setId(data.get("id").toString());
            }
            organization.setLogoff(data.get("logoff").toString());
            organization.setAddress(data.get("address").toString());
            organization.setAdmindivision(data.get("admindivision").toString());
            organization.setBuildingArea(data.get("buildingArea").toString());
            organization.setChemicalmedNum(data.get("chemicalmedNum").toString());
            organization.setChinesemedNum(data.get("chinesemedNum").toString());
            organization.setClassifyCode(data.get("classifyCode").toString());
            organization.setCreateDate(new Date());
            organization.setDirector(data.get("director").toString());
            organization.setEmail(data.get("email").toString());
            organization.setFoundDate(new Date());
            organization.setGrade(data.get("grade").toString());
            organization.setHostCode(data.get("hostCode").toString());
            organization.setInstitLevel(data.get("institLevel").toString());
            organization.setLegal(data.get("legal").toString());
            organization.setOrganizCode(data.get("organizCode").toString());
            organization.setOrganizName(data.get("organizName").toString());
            organization.setOrganizSecondName(data.get("organizSecondName").toString());
            organization.setOrganizType(data.get("organizType").toString());
            organization.setParentId(data.get("parentId").toString());
            if("".equals(data.get("pyCode"))){
                organization.setPyCode(PinYinUtils.getFirstSpell(data.get("organizName").toString()));
            }else{
                organization.setPyCode(data.get("pyCode").toString());
            }
            organization.setRegisterNumber(data.get("registerNumber").toString());
            organization.setStationNum(data.get("stationNum").toString());
            organization.setStreeCode(data.get("streeCode").toString());
            organization.setStreeName(data.get("streeName").toString());
            organization.setSubCode(data.get("subCode").toString());
            organization.setSubNum(data.get("subNum").toString());
            organization.setTelphone(data.get("telphone").toString());
            organization.setWebsite(data.get("website").toString());
            organization.setZipCode(data.get("zipCode").toString());
            return new Response(userService.saveOrUpdateUserOrganization(organization));
        }else if("office".equals(jsonParam.get("type"))){
            SysOffice office = new SysOffice();
            if("modify".equals(jsonParam.get("opt"))){
                office.setId(data.get("id").toString());
            }
            office.setAddress(data.get("address").toString());
            office.setEmail(data.get("email").toString());
            office.setHospitalArea(data.get("hospitalArea").toString());
            office.setHospitalDept(data.get("hospitalDept").toString());
            office.setKsxsgs(data.get("ksxsgs").toString());
            office.setKsxsgsCode(data.get("ksxsgsCode").toString());
            office.setLogoff(data.get("logoff").toString());
            office.setMedicalLab(data.get("medicalLab").toString());
            office.setOfficeCode(data.get("officeCode").toString());
            office.setOfficeName(data.get("officeName").toString());
            office.setOrganizType(data.get("organizType").toString());
            office.setOrganizCode(data.get("organizCode").toString());
            office.setOutPatientClinic(data.get("outPatientClinic").toString());
            office.setParentId(data.get("parentId").toString());
            office.setPlsx(data.get("plsx").toString());
            office.setPyCode(data.get("pyCode").toString());
            if(data.containsKey("ratedBed") && !"".equals(data.get("ratedBed"))){
                office.setRatedBed((int)data.get("ratedBed"));
            }
            office.setTelphone(data.get("telphone").toString());
            return new Response(userService.saveOrUpdateSysOffice(office));
        }else{
            throw new ModelOperationException(ErrorCodeAndMsg.HTTP_CODE_500);
        }
    }
    @RequestMapping(value = "/loadOrganTreeData.json", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String doLoadOrganTreeData() throws ModelOperationException{
        return JSONObject.toJSONString(userService.loadOrganTreeData());
    }
    @RequestMapping(value = "/loadOfficeTreeData.json", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String doLoadOfficeTreeData(@RequestBody JSONObject jsonParam) throws ModelOperationException{
        return JSONObject.toJSONString(userService.loadOfficeTreeData(jsonParam.getString("id"),jsonParam.getString("parentText")));
    }
    @RequestMapping(value = "/loadOfficeByPkey.json", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Response doLoadOfficeByPkey(@RequestBody JSONObject jsonParam) throws ModelOperationException{
        String pKey = jsonParam.getString("id");
        if(pKey==null || "".equals(pKey)){
            throw new ModelOperationException(ErrorCodeAndMsg.HTTP_CODE_415);
        }
        return new Response(userService.loadSysOffice(pKey));
    }
    @RequestMapping(value = "/loadAllSysRoles.json", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Response doLoadAllSysRoles(@RequestBody JSONObject jsonParam) throws ModelOperationException{
        String outDomainId = "";
        if(jsonParam.containsKey("outDomainId")){
            outDomainId = jsonParam.getString("outDomainId");
        }
        return new Response(userService.loadSysRoles(outDomainId));
    }
    @RequestMapping(value = "/saveOrUpdateSysRoles.json", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Response doSaveOrUpdateSysRoles(@RequestBody JSONObject jsonParam) throws ModelOperationException{
        String rolesName = jsonParam.getString("rolesName");
        if(rolesName==null || "".equals(rolesName)){
            logger.error("角色名字为空");
            throw new ModelOperationException(ErrorCodeAndMsg.HTTP_CODE_415);
        }
        String rolesCode = jsonParam.getString("rolesCode");
        if(rolesCode==null || "".equals(rolesCode)){
            logger.error("角色编码为空");
            throw new ModelOperationException(ErrorCodeAndMsg.HTTP_CODE_415);
        }
        String outDomainId = jsonParam.getString("outDomainId");
        if(outDomainId==null || "".equals(outDomainId)){
            logger.error("所在域为空");
            throw new ModelOperationException(ErrorCodeAndMsg.HTTP_CODE_415);
        }
        String status = jsonParam.getString("status");
        if(status==null || "".equals(status)){
            logger.error("角色状态为空");
            throw new ModelOperationException(ErrorCodeAndMsg.HTTP_CODE_415);
        }
        Date now = new Date();
        SysRoles sysRoles = new SysRoles();
        sysRoles.setRolesName(rolesName);
        sysRoles.setRolesCode(rolesCode);
        sysRoles.setOutDomainId(outDomainId);
        sysRoles.setStatus(status);
        if("modify".equals(jsonParam.getString("opt"))){
            String id = jsonParam.getString("id");
            if(id==null || "".equals(id)){
                throw new ModelOperationException(ErrorCodeAndMsg.HTTP_CODE_415);
            }
            sysRoles.setId(jsonParam.getString("id"));
            sysRoles.setCreateDate(jsonParam.getDate("createDate"));
        }else{
            sysRoles.setCreateDate(now);
        }
        sysRoles.setModifyDate(now);
        return new Response(userService.saveOrUpdateSysRoles(sysRoles));
    }
    @RequestMapping(value = "/loadMemusByParentId.json",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public Response doLoadMenusByParentId(@RequestBody JSONObject json) throws ModelOperationException{
        if(!json.containsKey("menuParentId")){
            throw new ModelOperationException(ErrorCodeAndMsg.HTTP_CODE_415);
        }
        List<SysMenus> data = userService.loadSysMenusByMenuParentIdAndStatus(json.getString("menuParentId"));
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        for(SysMenus sysMenus:data){
            Map<String,Object> map = new HashMap<String,Object>();
            map = JSON.parseObject(JSON.toJSONString(sysMenus));
            if("1".equals(sysMenus.getSubMenuStatus())){
                map.put("hasChildren",true);
            }else{
                map.put("hasChildren",false);
            }
            list.add(map);
        }
        return new Response(list);
    }
    @RequestMapping(value = "/queryMemusByPage.json",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public Map<String,Object> doQueryMemusByPage(@RequestBody JSONObject json) throws ModelOperationException{
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("status","1");
        int countParams = 1,pageNo=0,pageSize=25;
        if(json.containsKey("countParams") && !"".equals(json.get("countParams"))){
            countParams = json.getInteger("countParams");
        }
        switch (countParams){
            case 1:break;
            case 2:
                if(!json.containsKey("menuName")){
                    throw new ModelOperationException(ErrorCodeAndMsg.HTTP_CODE_415);
                }
                params.put("menuName",json.get("menuName"));
                break;
            case 3:
                if(!json.containsKey("menuCode")){
                    throw new ModelOperationException(ErrorCodeAndMsg.HTTP_CODE_415);
                }
                params.put("menuCode",json.get("menuCode"));
                break;
            case 4:
                if(!json.containsKey("outRoleId")){
                    throw new ModelOperationException(ErrorCodeAndMsg.HTTP_CODE_415);
                }
                params.put("outRoleId",json.get("outRoleId"));
                break;
            case 5:
                if(!json.containsKey("menuName") || !json.containsKey("menuCode")){
                    throw new ModelOperationException(ErrorCodeAndMsg.HTTP_CODE_415);
                }
                params.put("menuName",json.get("menuName"));
                params.put("menuCode",json.get("menuCode"));
                break;
            case 6:
                if(!json.containsKey("menuName") || !json.containsKey("outRoleId")){
                    throw new ModelOperationException(ErrorCodeAndMsg.HTTP_CODE_415);
                }
                params.put("menuName",json.get("menuName"));
                params.put("outRoleId",json.get("outRoleId"));
                break;
            case 7:
                if(!json.containsKey("menuCode") || !json.containsKey("outRoleId")){
                    throw new ModelOperationException(ErrorCodeAndMsg.HTTP_CODE_415);
                }
                params.put("menuCode",json.get("menuCode"));
                params.put("outRoleId",json.get("outRoleId"));
                break;
            case 8:
                if(!json.containsKey("menuName") || !json.containsKey("menuCode") || !json.containsKey("outRoleId")){
                    throw new ModelOperationException(ErrorCodeAndMsg.HTTP_CODE_415);
                }
                params.put("menuName",json.get("menuName"));
                params.put("menuCode",json.get("menuCode"));
                params.put("outRoleId",json.get("outRoleId"));
                break;
            default:
                countParams = 1;
                break;
        }
        if(json.containsKey("pageNo")){
            pageNo = json.getInteger("pageNo")-1;
        }
        if(json.containsKey("pageSize")){
            pageSize = json.getInteger("pageSize");
        }
        params.put("pageNo",pageNo);
        params.put("pageSize",pageSize);
        Page<SysMenus> pageSysMenus = userService.loadSysMenusByPage(params,countParams);
        Map<String,Object> resp = new HashMap<String,Object>();
        resp.put("code","200");
        resp.put("msg","查询成功");
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        for(SysMenus sysMenus:pageSysMenus.getContent()){
            Map<String,Object> map = new HashMap<String,Object>();
            map = JSON.parseObject(JSON.toJSONString(sysMenus));
            if("1".equals(sysMenus.getSubMenuStatus())){
                map.put("hasChildren",true);
            }else{
                map.put("hasChildren",false);
            }
            list.add(map);
        }
        resp.put("data",list);
        resp.put("totalCount",pageSysMenus.getTotalElements());
        resp.put("pageNo",pageNo+1);
        resp.put("pageSize",pageSize);
        return resp;
    }
    @RequestMapping(value = "/saveOrUpdateSysMenu.json", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Response doSaveOrUpdateSysMenu(@RequestBody JSONObject json){
        SysMenus sysMenus = new SysMenus();
        if("modify".equals(json.getString("opt"))){
            if(!json.containsKey("id")){
                throw new ModelOperationException(ErrorCodeAndMsg.HTTP_CODE_415);
            }
            sysMenus.setId(json.getString("id"));
            if(!json.containsKey("createDate")){
                throw new ModelOperationException(ErrorCodeAndMsg.HTTP_CODE_415);
            }
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(json.getLong("createDate"));
            sysMenus.setCreateDate(cal.getTime());
        }else{
            sysMenus.setCreateDate(new Date());
        }
        if(!json.containsKey("menuName")){
            throw new ModelOperationException(ErrorCodeAndMsg.HTTP_CODE_415);
        }
        sysMenus.setMenuName(json.getString("menuName"));
        if(!json.containsKey("menuCode")){
            throw new ModelOperationException(ErrorCodeAndMsg.HTTP_CODE_415);
        }
        sysMenus.setMenuCode(json.getString("menuCode"));
        if(!json.containsKey("outRoleId")){
            throw new ModelOperationException(ErrorCodeAndMsg.HTTP_CODE_415);
        }
        sysMenus.setOutRoleId(json.getString("outRoleId"));
        if(!json.containsKey("menuLevel")){
            throw new ModelOperationException(ErrorCodeAndMsg.HTTP_CODE_415);
        }
        sysMenus.setMenuLevel(json.getInteger("menuLevel"));
//        if(!json.containsKey("menuParentId")){
//            throw new ModelOperationException(ErrorCodeAndMsg.HTTP_CODE_415);
//        }
        sysMenus.setMenuParentId(json.getString("menuParentId"));
        if(!json.containsKey("subMenuStatus")){
            throw new ModelOperationException(ErrorCodeAndMsg.HTTP_CODE_415);
        }
        sysMenus.setSubMenuStatus(json.getString("subMenuStatus"));
        if(!json.containsKey("menuPath")){
            throw new ModelOperationException(ErrorCodeAndMsg.HTTP_CODE_415);
        }
        sysMenus.setMenuPath(json.getString("menuPath"));
        if(!json.containsKey("status")){
            throw new ModelOperationException(ErrorCodeAndMsg.HTTP_CODE_415);
        }
        sysMenus.setStatus(json.getString("status"));
        Date now = new Date();
        sysMenus.setModifyDate(now);
        sysMenus = userService.saveOrUpdateSysMenus(sysMenus);
        if(json.getInteger("menuLevel")>1){
            userService.updateSysMenus("1",now,json.getString("menuParentId"));
        }
        return new Response(sysMenus);
    }
    @RequestMapping(value = "/updateSysMenus.json", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String updateSysMenus(@RequestBody JSONObject json){
        userService.updateSysMenus(json.getString("subMenuStatus"),new Date(),json.getString("id"));
        return JSON.toJSONString("hello:\"123\"");
    }
    @RequestMapping(value = "/dataToVueTree.json", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String dataToVueTree(@RequestBody JSONObject jsonParam) throws ModelOperationException{
        List<Map<String,Object>> list = (List<Map<String, Object>>) jsonParam.get("items");
        int i=0,j=0,k=0;
        List<Map<String,Object>> treeList = new ArrayList<Map<String,Object>>();
        for(Map<String,Object> map:list){
            String key = map.get("key").toString();
            Map<String,Object> treeMap = new HashMap<String,Object>();
            Map<String,Object> properties = new HashMap<String,Object>();
            properties = (Map<String, Object>) map.get("properties");
            String parentId = properties.get("parent").toString();
            treeMap.put("id",key);
            treeMap.put("name",map.get("text"));
            treeMap.put("parentId",parentId);
            if(key.length()==2){
                i++;
                j=0;
                treeMap.put("rank",i);
            }else if(key.length()==4){
                j++;
                k=0;
                treeMap.put("rank",j);
            }else if(key.length()==6){
                k++;
                treeMap.put("rank",k);
            }
            treeList.add(treeMap);
        }
        return JSONObject.toJSONString(treeList);
    }
    @RequestMapping(value = "/loadOrganOfficeTreeData.json", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String doLoadOrganOfficeTreeData(@RequestBody JSONObject json) throws ModelOperationException{
        if(!json.containsKey("type")){
           throw new ModelOperationException(ErrorCodeAndMsg.HTTP_CODE_415);
        }
        return JSONObject.toJSONString(userService.loadOrganOfficeTreeData(json.getString("type"),json.getString("parentId"),json.getString("parentText")));
    }
    @RequestMapping(value = "/loadSysPersonsByOrganIdOrOfficeId.json", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Response doLoadSysPersonsByOrganIdOrOfficeId(@RequestBody JSONObject json) throws ModelOperationException{
        if(!json.containsKey("type") || !json.containsKey("id")){
            throw new ModelOperationException(ErrorCodeAndMsg.HTTP_CODE_415);
        }
        return new Response(userService.findSysPersonnelByOrganIdOrOfficeId(json.getString("type"),json.getString("id")));
    }
    @RequestMapping(value = "/loadOrganTreeDataDic.json", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Response doLoadOrganTreeDataDic() throws ModelOperationException{
        return new Response(userService.loadOrganTreeData("1"));
    }
    @RequestMapping(value = "/loadOfficeTreeDataDic.json", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Response doLoadOfficeTreeDataDic(@RequestBody JSONObject json) throws ModelOperationException{
        if(!json.containsKey("organId")){
            throw new ModelOperationException(ErrorCodeAndMsg.HTTP_CODE_415);
        }
        return new Response(userService.loadOfficeTreeDataByOrganId("1",json.getString("organId")));
    }
    @RequestMapping(value = "/saveOrUpdateSysPersonnel.json", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Response doSaveOrUpdateSysPersonnel(@RequestBody JSONObject json) throws ModelOperationException{
        Map<String,Object> map = JSON.parseObject(json.toJSONString(),Map.class);
        SysPersonnel p = new SysPersonnel();
        if("modify".equals(json.getString("opt"))){
            if(!json.containsKey("id")){
                throw new ModelOperationException(ErrorCodeAndMsg.HTTP_CODE_415);
            }
        }
        if(json.getString("organizCode")==null || "".equals(json.getString("organizCode"))){
            throw new ModelOperationException(ErrorCodeAndMsg.HTTP_CODE_415);
        }
//        map.remove("opt");
        p = JSON.parseObject(JSON.toJSONString(map),SysPersonnel.class);
        userService.saveOrUpdateSysPersonnel(p);
        return new Response(p);
    }
    @RequestMapping(value = "/loadSysPersonnel.json", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Response doLoadSysPersonnel(@RequestBody JSONObject json) throws ModelOperationException{
        String filedType = json.getString("filedType");
        String filedValue = json.getString("filedValue");
        String type = json.getString("type");
        String typeValue = json.getString("typeValue");
        List<SysPersonnel> list = userService.loadSysPersonnels(filedType,filedValue,type,typeValue);
        return new Response(list);
    }
    @RequestMapping(value = "/loadBaseUserMixPersonnels.json", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Response doLoadBaseUserMixPersonnels(@RequestBody JSONObject json) throws ModelOperationException{
        if(!json.containsKey("organId")){
            logger.error("缺少机构ID");
            throw new ModelOperationException(ErrorCodeAndMsg.HTTP_CODE_415);
        }
        String organId = json.getString("organId");
        if(json.containsKey("filedName")){
            if(!json.containsKey("filedValue")){
                logger.error("缺少字段对应的值数据");
                throw new ModelOperationException(ErrorCodeAndMsg.HTTP_CODE_415);
            }
        }
        String filedName = json.getString("filedName");
        String filedValue = json.getString("filedValue");
        return new Response(userService.loadUserMixDatas(filedName==null?"":filedName,filedValue,organId));
    }
    @RequestMapping(value = "/loadBaseUserRolesByUserId.json", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Response doLoadBaseUserRolesByUserId(@RequestBody JSONObject json) throws ModelOperationException{
        if(!json.containsKey("userId")){
            logger.error("缺少参数userId");
            throw new ModelOperationException(ErrorCodeAndMsg.HTTP_CODE_415);
        }
        String userId = json.getString("userId");
        return new Response(userService.loadBaseUserRolesByUserId(userId));
    }
    @RequestMapping(value = "/loadDomainAndRolesAndOrgan.json", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Response doLoadDomainAndRolesAndOrgan() throws ModelOperationException{
        return new Response(userService.loadDomainAndRolesAndOrgan());
    }
    @RequestMapping(value = "/saveOrUpdateUserInfo.json", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Response doSaveOrUpdateUserInfo(@RequestBody JSONObject json) throws ModelOperationException{
        if(!json.containsKey("baseUser")){
            logger.error("无用户数据");
            throw new ModelOperationException(ErrorCodeAndMsg.HTTP_CODE_415);
        }
        BaseUser user = json.getObject("baseUser",BaseUser.class);
        if(!json.containsKey("baseUserRoles")){
            logger.error("无角色数据");
            throw new ModelOperationException(ErrorCodeAndMsg.HTTP_CODE_415);
        }
        List<Map<String,Object>> list = json.getObject("baseUserRoles",List.class);
        userService.saveOrUpdateUserInfo(user,list);
        return new Response(true);
    }
    @RequestMapping(value = "/saveOrUpdateUserCenterKey.json", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Response doSaveOrUpdateUserCenterKey(@RequestBody JSONObject json) throws ModelOperationException{
        if(!json.containsKey("opt")){
            throw new ModelOperationException(ErrorCodeAndMsg.HTTP_CODE_415);
        }
        String opt = json.getString("opt");
        UserCenterKey ucKey = new UserCenterKey();
        if("create".equals(opt)){
            String uuid = UUID.randomUUID().toString();
            uuid = uuid.substring(0, 8) + uuid.substring(9, 13) + uuid.substring(14, 18) + uuid.substring(19, 23);
            ucKey.setCenterKey(uuid);
            ucKey.setCreateDate(new Date());
            ucKey.setModifyDate(new Date());
            ucKey.setStatus("1");
        }else{
            ucKey.setId(json.getString("id"));
            ucKey.setCenterKey(json.getString("centerKey"));
            ucKey.setCreateDate(json.getDate("createDate"));
            ucKey.setModifyDate(new Date());
            ucKey.setStatus(json.getString("status"));
        }
        userService.saveOrUpdateUserCenterKey(ucKey);
        return new Response(true);
    }
    @RequestMapping(value = "/loadAllUserCenterKey.json", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Response doLoadAllUserCenterKey(@RequestBody JSONObject json) throws ModelOperationException{
        List<UserCenterKey>  list = userService.loadAllUserCenterKey(json.getString("status"));
        return new Response(list);
    }
    @RequestMapping(value = "/loadUserCenterKeyByConditions.json", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Response doLoadUserCenterKeyByConditions(@RequestBody JSONObject json) throws ModelOperationException{
        List<UserCenterKey> list;
        String status = "0";
        if(json.containsKey("status")){
            status = json.getString("status");
        }
        if(json.containsKey("centerKey")){
            list = userService.loadAllUserCenterKeyByContions(status,json.getString("centerKey"));
        }else{
            list = userService.loadAllUserCenterKey(status);
        }
        return new Response(list);
    }
    @RequestMapping(value = "/loadBaseUserRelation.json", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Response doLoadBaseUserRelation(@RequestBody JSONObject json) throws ModelOperationException{

        return new Response(userService.loadBaseUserRelationByConditions(json.getString("type"),json.getString("organId"),json.getString("val")));
    }

    @RequestMapping(value = "/updateBaseUserRelation.json", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Response doUpdateBaseUserRelation(@RequestBody JSONObject json) throws ModelOperationException{
        userService.updateBaseUserRelation(json.getString("localUserId"),json.getString("username"),new Date(),json.getString("id"));
        return new Response(true);
    }

    @RequestMapping(value = "/vaildCenterKeyAndUesrId.json", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Response doVaildCenterKeyAndUesrId(@RequestBody JSONObject json) throws ModelOperationException{
        /**
         * 返回值：0密钥无效；1密钥和密码合法，2密码错误
         */
        return new Response(userService.vaildCenterKeyAndUesrId(json.getString("centerKey"),json.getString("localUserId"),json.getString("pwd")));
    }

    @RequestMapping(value = "/saveOrUpdateBaseUserRelation.json", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Response doSaveOrUpdateBaseUserRelation(@RequestBody JSONObject json) throws ModelOperationException{
        try {
            userService.saveOrUpdateBaseUserRelation(json);
        } catch (NoSuchAlgorithmException e) {
            logger.error(e.getMessage());
            throw new ModelOperationException(ErrorCodeAndMsg.HTTP_CODE_500);
        }
        return new Response(true);
    }
    @RequestMapping(value = "/delBaseUserRelationById.json", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Response doDelBaseUserRelationById(@RequestBody JSONObject json){
        userService.delBaseUserRelationById(json.getString("id"),json.getString("centerKey"));
        return new Response(true);
    }
    @RequestMapping(value = "/loadSysPersonnelByOrganId.json", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Response doLoadSysPersonnelByOrganId(@RequestBody JSONObject json){
        return new Response(userService.loadSysPersonnelByOrganId("1",json.getString("organId")));
    }
    @RequestMapping(value = "/loadDetailInfoByOutUserIdAndPwd.json", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String,Object> doLoadDetailInfoByOutUserIdAndPwd(@RequestBody JSONObject json)  throws ModelOperationException{
        if(!json.containsKey("centerKey")){
            logger.error("参数错误：请传入平台密钥");
            throw new ModelOperationException(ErrorCodeAndMsg.HTTP_CODE_415);
        }
        if(!json.containsKey("userId")){
            logger.error("参数错误：请传入用户ID");
            throw new ModelOperationException(ErrorCodeAndMsg.HTTP_CODE_415);
        }
        if(!json.containsKey("pwd")){
            logger.error("参数错误：请传入密码");
            throw new ModelOperationException(ErrorCodeAndMsg.HTTP_CODE_415);
        }
        Map<String,Object> map;
        try {
            map = userService.loadDetailInfoByOutUserIdAndPwd(json.getString("centerKey"),json.getString("userId"),json.getString("pwd"));
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new ModelOperationException(ErrorCodeAndMsg.HTTP_CODE_500);
        }
        return map;
    }

}
