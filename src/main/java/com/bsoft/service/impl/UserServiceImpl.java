package com.bsoft.service.impl;/**
 * Arthur Administrator
 * date 2019/12/27.
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bsoft.entity.*;
import com.bsoft.message.ErrorCodeAndMsg;
import com.bsoft.message.ModelOperationException;
import com.bsoft.repository.*;
import com.bsoft.service.UserService;
import com.bsoft.utils.RSAEncrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 *@ClassName UserServiceImpl
 *@Description TODO
 *@Author Administrator
 *@Date 2019/12/27 17:34
 *@Version 1.0
 **/
@Service
public class UserServiceImpl implements UserService{
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private TestUserRepository testUserRepository;
    @Autowired
    private UserOrganizationRepository userOrganizationRepository;

    @Autowired
    private BaseUserRepository baseUserRepository;

    @Autowired
    private SysDomainRepository sysDomainRepository;

    @Autowired
    private SysOfficeRepository sysOfficeRepository;

    @Autowired
    private SysRolesRepository sysRolesRepository;

    @Autowired
    private SysMenuPageRepository sysMenuPageRepository;

    @Autowired
    private SysMenuRepository sysMenuRepository;
    @Autowired
    private SysPersonnelRepository sysPersonnelRepository;
    @Autowired
    private BaseUserRolesRepository baseUserRolesRepository;
    @Autowired
    private UserCenterKeyRepository userCenterKeyRepository;
    @Autowired
    private BaseUserRelationRepository baseUserRelationRepository;

    @PersistenceContext
    EntityManager entityManagerPrimary;

    @Override
    public void addTestUser(TestUser testUser) {
        testUserRepository.save(testUser);
    }

    @Override
    public void addUserOrganization(UserOrganization organization) {
        userOrganizationRepository.save(organization);
    }

    @Override
    public boolean existsById(String id) {
        boolean exists = false;
        BaseUser user = baseUserRepository.getBaseUserByPwd(id);
        if(user!=null){
            exists = true;
        }
        return exists;
    }

    @Override
    public List<BaseUser> findAll() {
        return (List<BaseUser>) baseUserRepository.findAll();
    }

    @Override
    public BaseUser checkLogonUser(String name, String password,String status) {
        List<BaseUser> list = baseUserRepository.getLogonUsers(name,name,password,status);
        if(list!=null && list.size()>0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public SysDomain saveOrUpdateSysDomain(SysDomain sysDomain) {
        return sysDomainRepository.save(sysDomain);
    }
    @Override
    public UserOrganization saveOrUpdateUserOrganization(UserOrganization userOrganization) {
        return userOrganizationRepository.save(userOrganization);
    }
    @Override
    public SysOffice saveOrUpdateSysOffice(SysOffice sysOffice) {
        return sysOfficeRepository.save(sysOffice);
    }

    @Override
    public List<SysDomain> getSysDomain(String status) {
        return sysDomainRepository.getAllSysDomainsByStatusOrderByCreateDateDesc(status);
    }

//    public List<SysDomain> querySysDomainByCondition(Map<String,Object> params) {
//        StringBuffer sql = new StringBuffer(0);
//        sql.append("SELECT ID AS ID,DOMAIN_CODE AS DOMAIN_CODE,DOMAIN_NAME AS DOMAIN_NAME,REMARK AS REMARK, STATUS AS STATUS,DECODE(STATUS,'1','启用','0','禁用') AS STATUS_TEXT,DOMAIN_URL AS DOMAIN_URL,TO_CHAR(CREATE_DATE,'YYYY-MM-DD HH24:MI:SS') AS CREATE_DATE FROM SYS_DOMAIN  WHERE ");
//        int i = 0,length = params.size();
//        for(String key : params.keySet()){
//            i++;
//            sql.append(key).append(" = ").append(params.get(key));
//            if(i<length){
//                sql.append(" and ");
//            }
//        }
//        sql.append(" ORDER BY CREATE_DATE DESC");
//        Query query = entityManagerPrimary.createNativeQuery(sql.toString(),SysDomain.class);
//        return query.getResultList();
//    }

    @Override
    public Map<String,Object> querySysDomainByCondition(Map<String,Object> params,int pageNo,int pageSize) {
        StringBuffer sql = new StringBuffer(0);
        StringBuffer countSql = new StringBuffer(0);
        countSql.append("select count(1) from SYS_DOMAIN where ");
        sql.append("SELECT ID AS ID,DOMAIN_CODE AS DOMAIN_CODE,DOMAIN_NAME AS DOMAIN_NAME,REMARK AS REMARK, STATUS AS STATUS,DECODE(STATUS,'1','启用','0','禁用') AS STATUS_TEXT,DOMAIN_URL AS DOMAIN_URL,TO_CHAR(CREATE_DATE,'YYYY-MM-DD HH24:MI:SS') AS CREATE_DATE FROM SYS_DOMAIN  WHERE ");
        int i = 0,length = params.size();
        for(String key : params.keySet()){
            i++;
            sql.append(key).append(" = '").append(params.get(key)).append("'");
            countSql.append(key).append(" = '").append(params.get(key)).append("'");
            if(i<length){
                sql.append(" and ");
                countSql.append(" and ");
            }
        }
        sql.append(" ORDER BY CREATE_DATE DESC");
        Query dataQuery = entityManagerPrimary.createNativeQuery(sql.toString(),SysDomain.class);
        Query countQuery = entityManagerPrimary.createNativeQuery(countSql.toString());
        dataQuery.setFirstResult((pageNo-1)*pageSize);
        dataQuery.setMaxResults(pageSize);
        List<SysDomain> list = dataQuery.getResultList();
        BigDecimal count = (BigDecimal)countQuery.getSingleResult();
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("data",list);
        map.put("totalCount",count);
        map.put("code","200");
        map.put("msg","查询成功");
        return map;
    }
    public Map<String, Object> queryUserOrganByCondition(Map<String, Object> params, int pageNo, int pageSize) {
        StringBuffer sql = new StringBuffer(0);
        StringBuffer countSql = new StringBuffer(0);
        countSql.append("select count(1) from USER_ORGANIZATION where ");
        sql.append("SELECT * FROM USER_ORGANIZATION  WHERE ");
        int i = 0,length = params.size();
        for(String key : params.keySet()){
            if(i>0 && i<length){
                if(key.equalsIgnoreCase("parent_id")){
                    sql.append(" or ");
                    countSql.append(" or ");
                }else{
                    sql.append(" and ");
                    countSql.append(" and ");
                }
            }
            sql.append(key).append(" = '").append(params.get(key)).append("'");
            countSql.append(key).append(" = '").append(params.get(key)).append("'");
            i++;
        }
        sql.append(" ORDER BY CREATE_DATE DESC");
        Query dataQuery = entityManagerPrimary.createNativeQuery(sql.toString(),UserOrganization.class);
        Query countQuery = entityManagerPrimary.createNativeQuery(countSql.toString());
        dataQuery.setFirstResult((pageNo-1)*pageSize);
        dataQuery.setMaxResults(pageSize);
        List<SysDomain> list = dataQuery.getResultList();
        BigDecimal count = (BigDecimal)countQuery.getSingleResult();
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("data",list);
        map.put("totalCount",count);
        map.put("code","200");
        map.put("msg","查询成功");
        return map;
    }
    /*** 
     * @Description: 查询机构表，并组装成树形结构数据
     * @Author: Vee
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     * @Date:  2020/1/8
     **/ 
    public Map<String,Object> loadOrganTreeData() throws ModelOperationException {
        List<TreeDTO> list = userOrganizationRepository.findAllUserOrganizations("1");
        return getTreeData(list,"","");
    }
    /***
     * @Description: 查询科室表，并组装成树形结构数据
     * @Author: Vee
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     * @Date:  2020/1/8
     **/
    public Map<String,Object> loadOfficeTreeData(String organId,String organName) throws ModelOperationException {
        List<TreeDTO> list = sysOfficeRepository.findAllSysOffices("1",organId);
        return getTreeData(list,organId,organName);
    }
    /***
     * 根据主键id，查询科室信息
     * @Author: Vee
     * @Param: pkey 主键
     * @return: SysOffice
     * @Date:  2020/1/10
     **/
    public SysOffice loadSysOffice(String pkey){
        return sysOfficeRepository.findAllById(pkey);
    }
    public List<SysRoles> loadSysRoles(String outDomainId){
        List<SysRoles> list = new ArrayList<SysRoles>();
        if("".equals(outDomainId)){
            list = sysRolesRepository.getAllSysRolesByStatusOrderByModifyDateDesc("1");
        }else{
            list = sysRolesRepository.getAllSysRolesByStatusAndOutDomainIdOrderByModifyDateDesc("1",outDomainId);
        }
        return list;
    }
    public SysRoles saveOrUpdateSysRoles(SysRoles sysRoles){
        return sysRolesRepository.save(sysRoles);
    }

    @Override
    public Page<SysMenus> loadSysMenusByPage(Map<String,Object> params,int countParams) throws ModelOperationException{
        Page<SysMenus> pages;
        Pageable pageable =PageRequest.of((int)params.get("pageNo"),(int)params.get("pageSize"),Sort.by(Sort.Order.desc("modifyDate")));
        switch (countParams){
            case 1:
                pages =  sysMenuPageRepository.findAllByStatusAndMenuParentIdIsNull(params.get("status").toString(),pageable);
                break;
            case 2:
                pages =  sysMenuPageRepository.findAllByStatusAndMenuNameAndMenuParentIdIsNull(params.get("status").toString(),params.get("menuName").toString(),pageable);
                break;
            case 3:
                pages =  sysMenuPageRepository.findAllByStatusAndMenuCodeAndMenuParentIdIsNull(params.get("status").toString(),params.get("menuCode").toString(),pageable);
                break;
            case 4:
                pages =  sysMenuPageRepository.findAllByStatusAndOutRoleIdAndMenuParentIdIsNull(params.get("status").toString(),params.get("outRoleId").toString(),pageable);
                break;
            case 5:
                pages =  sysMenuPageRepository.findAllByStatusAndMenuNameAndMenuCodeAndMenuParentIdIsNull(params.get("status").toString(),params.get("menuName").toString(),params.get("menuCode").toString(),pageable);
                break;
            case 6:
                pages =  sysMenuPageRepository.findAllByStatusAndMenuNameAndOutRoleIdAndMenuParentIdIsNull(params.get("status").toString(),params.get("menuName").toString(),params.get("outRoleId").toString(),pageable);
                break;
            case 7:
                pages =  sysMenuPageRepository.findAllByStatusAndMenuCodeAndOutRoleIdAndMenuParentIdIsNull(params.get("status").toString(),params.get("menuCode").toString(),params.get("outRoleId").toString(),pageable);
                break;
            case 8:
                pages =  sysMenuPageRepository.findAllByStatusAndMenuNameAndMenuCodeAndOutRoleIdAndMenuParentIdIsNull(params.get("status").toString(),params.get("menuName").toString(),params.get("menuCode").toString(),params.get("outRoleId").toString(),pageable);
                break;
            default:
                throw new ModelOperationException(ErrorCodeAndMsg.HTTP_CODE_500);
        }

        return pages;
    }

    @Override
    public List<SysMenus> loadSysMenusByMenuParentIdAndStatus(String parentId) {
        return sysMenuRepository.getAllByMenuParentIdAndStatus(parentId,"1");
    }

    @Override
    public SysMenus saveOrUpdateSysMenus(SysMenus sysMenus) {
        return sysMenuRepository.save(sysMenus);
    }

    @Override
    public void updateSysMenus(String subMenuStatus, Date modifyDte, String pkey) {
        sysMenuRepository.updateSysMenus(subMenuStatus,modifyDte,pkey);
    }
    /**
     * 加载机构和科室树
     * @Author: Vee
     * @Param: []
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     * @Date:  2020/1/15
     **/
    @Override
    public Map<String, Object> loadOrganOfficeTreeData(String type,String parentId,String parentText) throws ModelOperationException {
        Map<String,Object> treeDataMap = new HashMap<String,Object>();
        List<TreeData> treeDataList = new ArrayList<>();
        List<TreeDTO> list = new ArrayList<>();
        if("1".equals(type)){
            if(parentId==null){
                list = userOrganizationRepository.findAllUserOrganizationsByParentIdIsNull("1");
            }else{
                list = userOrganizationRepository.findAllUserOrganizationsByParentId("1",parentId);
            }
            if(list==null || list.size()==0){
                treeDataMap.put("totalCount",0);
                treeDataMap.put("data",null);
                return treeDataMap;
            }
            treeDataList = this.getTreeDataNoChildren(list,parentId,parentText);
            if(parentId!=null && !"".equals(parentId)){
                List<TreeDTO> officeList = sysOfficeRepository.findAllSysOfficesByOrganizCode("1",parentId);
                if(officeList==null || officeList.size()==0){
//                    treeDataMap.put("totalCount",treeDataList.size());
                    treeDataMap.put("data",treeDataList);
                    return treeDataMap;
                }
                List<TreeData> treeOfficeList = this.getTreeDataNoChildren(officeList,parentId,parentText);
                treeDataList.addAll(treeOfficeList);
            }
        }else{
            if(parentId!=null && !"".equals(parentId)){
                List<TreeDTO> officeList = sysOfficeRepository.findAllSysOfficesByOrganizCode("1",parentId);
                treeDataList = this.getTreeDataNoChildren(officeList,parentId,parentText);
            }
        }
        treeDataMap.put("totalCount",treeDataList==null?0:treeDataList.size());
        treeDataMap.put("data",treeDataList);
        return treeDataMap;
    }
    /**
     * 根据机构id或者科室id查询用户信息
     * @Author: Vee
     * @Param: type:1机构，2科室
     * @Param: id:机构id或者科室id
     * @return: java.util.List<com.bsoft.entity.SysPersonnel> 人员集合
     * @Date:  2020/1/15
     **/
    @Override
    public List<SysPersonnel> findSysPersonnelByOrganIdOrOfficeId(String type, String id) {
        List<SysPersonnel> list = new ArrayList<>();
        if("1".equals(type)){
            list=sysPersonnelRepository.findSysPersonnelsByOrganizCodeAndLogOff(id,"1");
        }else{
            list=sysPersonnelRepository.findSysPersonnelsByOfficeCodeAndLogOff(id,"1");
        }
        return list;
    }

    @Override
    public List<TreeEntityDTO> loadOrganTreeData(String logoff) {
        return userOrganizationRepository.findTreeUserOrganizations(logoff);
    }

    @Override
    public List<TreeEntityDTO> loadOfficeTreeDataByOrganId(String logoff, String organId) {
        return sysOfficeRepository.findTreeSysOfficesByOrganizCode(logoff,organId);
    }

    @Override
    public SysPersonnel saveOrUpdateSysPersonnel(SysPersonnel sysPersonnel) {
        return sysPersonnelRepository.save(sysPersonnel);
    }

    @Override
    public List<SysPersonnel> loadSysPersonnels(String filedType,String filedValue,String type,String typeVal) throws ModelOperationException{
        List<SysPersonnel> list = new ArrayList<SysPersonnel>();
        String logoff="1";
        switch (filedType){
            case "1":
                if("1".equals(type)){
                    list = sysPersonnelRepository.findSysPersonnelsByOrganizCodeAndLogOffAndPersonId(typeVal,logoff,filedValue);
                }else{
                    list = sysPersonnelRepository.findSysPersonnelsByOfficeCodeAndLogOffAndPersonId(typeVal,logoff,filedValue);
                }
                break;
            case "2":
                if("1".equals(type)){
                    list = sysPersonnelRepository.findSysPersonnelsByOrganizCodeAndLogOffAndGh(typeVal,logoff,filedValue);
                }else{
                    list = sysPersonnelRepository.findSysPersonnelsByOfficeCodeAndLogOffAndGh(typeVal,logoff,filedValue);
                }
                break;
            case "3":
                if("1".equals(type)){
                    list = sysPersonnelRepository.findSysPersonnelsByOrganizCodeAndLogOffAndPersonName(typeVal,logoff,filedValue);
                }else{
                    list = sysPersonnelRepository.findSysPersonnelsByOfficeCodeAndLogOffAndPersonName(typeVal,logoff,filedValue);
                }
                break;
            case "4":
                if("1".equals(type)){
                    list = sysPersonnelRepository.findSysPersonnelsByOrganizCodeAndLogOffAndCardNum(typeVal,logoff,filedValue);
                }else{
                    list = sysPersonnelRepository.findSysPersonnelsByOfficeCodeAndLogOffAndCardNum(typeVal,logoff,filedValue);
                }
                break;
            case "5":
                if("1".equals(type)){
                    list = sysPersonnelRepository.findSysPersonnelsByOrganizCodeAndLogOffAndMobile(typeVal,logoff,filedValue);
                }else{
                    list = sysPersonnelRepository.findSysPersonnelsByOfficeCodeAndLogOffAndMobile(typeVal,logoff,filedValue);
                }
                break;
            default:
                throw new ModelOperationException(ErrorCodeAndMsg.HTTP_CODE_500);
        }
        return list;
    }

    @Override
    public List<UserMixEntity> loadUserMixDatas(String filedName,String filedValue,String organId) {
        List<UserMixEntity> list;
        String status = "1";
        switch (filedName){
            case "personId":
                list = baseUserRepository.getUserMixsByStatusAndOrganCodeAndPersonnelId(status,organId,filedValue);
                break;
            case "personName":
                list = baseUserRepository.getUserMixsByStatusAndOrganCodeAndPersonName(status,organId,filedValue);
                break;
            case "cardNum":
                list = baseUserRepository.getUserMixsByStatusAndOrganCodeAndCardNum(status,organId,filedValue);
                break;
            default:
                list = baseUserRepository.getUserMixsByStatusAndOrganCode(status,organId);
                break;
        }
        return list;
    }

    @Override
    public List<BaseUserRolesDTO> loadBaseUserRolesByUserId(String userId) {
        return baseUserRolesRepository.getMixBaseUserRolesByUserIdAndLogoff(userId,"1");
    }

    @Override
    public Map<String, Object> loadDomainAndRolesAndOrgan() {
        Map<String,Object> map = new HashMap<String,Object>();
        List<UserOrganization> organList = userOrganizationRepository.findAllByLogoff("1");
        List<SysRoles> sysRolesList = sysRolesRepository.getAllSysRolesByStatusOrderByModifyDateDesc("1");
        List<SysDomain> sysDomainList = sysDomainRepository.getAllSysDomainsByStatusOrderByCreateDateDesc("1");
        map.put("organData",organList);
        map.put("rolesData",sysRolesList);
        map.put("domainData",sysDomainList);
        return map;
    }

    @Override
    public void saveOrUpdateUserInfo(BaseUser baseUser, List<Map<String,Object>> list) throws ModelOperationException{
        Date now = new Date();
        if(baseUser.getCreatedt()==null){
            baseUser.setCreatedt(now);
        }
        baseUser = baseUserRepository.save(baseUser);
        for(Map<String,Object> map:list){
            BaseUserRoles baseUserRoles = JSON.parseObject(JSON.toJSONString(map),BaseUserRoles.class);
            baseUserRoles.setUserId(baseUser.getId());
            if(baseUserRoles.getCreateTime()==null){
                baseUserRoles.setCreateTime(now);
            }
            if(baseUserRoles.getLastLoginTime()==null){
                baseUserRoles.setLastLoginTime(now);
            }
            baseUserRolesRepository.save(baseUserRoles);
        }
    }

    @Override
    public void saveOrUpdateUserCenterKey(UserCenterKey ucKey) {
        userCenterKeyRepository.save(ucKey);
    }

    @Override
    public List<UserCenterKey> loadAllUserCenterKey(String status) {
        if("0".equals(status)){
            return userCenterKeyRepository.getAllUserCenterKeyByStatusIsNotOrderByModifyDateDesc(status);
        }else{
            return userCenterKeyRepository.getAllUserCenterKeyByStatusOrderByModifyDateDesc(status);
        }
    }

    @Override
    public List<UserCenterKey> loadAllUserCenterKeyByContions(String status, String centerKey) {
        if("0".equals(status)){
            return userCenterKeyRepository.getAllUserCenterKeyByStatusIsNotAndCenterKeyOrderByModifyDateDesc(status,centerKey);
        }else{
            return userCenterKeyRepository.getAllUserCenterKeyByStatusAndCenterKeyOrderByModifyDateDesc(status,centerKey);
        }
    }

    @Override
    public List<BaseUserRelationEntityDTO> loadBaseUserRelationByConditions(String type,String organId,String filedValue) {
        List<BaseUserRelationEntityDTO> list;
        switch (type){
            case "0":
                list = baseUserRelationRepository.getBaseUserRelationByOrganId(organId);
                break;
            case "1":
                list = baseUserRelationRepository.getBaseUserRelationByOrganIdAndLocalUserId(organId,filedValue);
                break;
            case "2":
                list = baseUserRelationRepository.getBaseUserRelationByOrganIdAndRemoteUserId(organId,filedValue);
                break;
            case "3":
                list = baseUserRelationRepository.getBaseUserRelationByOrganIdAndUsername(organId,filedValue);
                break;
            default:
                list = baseUserRelationRepository.getBaseUserRelationByOrganId(organId);
                break;
        }
        return list;
    }

    @Override
    public void updateBaseUserRelation(String localUserId,String username, Date modifyDate, String id) {
        baseUserRelationRepository.updateBaseUserRelationById(localUserId,username,modifyDate,id);
    }

    @Override
    public int vaildCenterKeyAndUesrId(String centerKey,String userId,String pwd) {
        int status = 0;
        List<UserCenterKey> ucKeys = userCenterKeyRepository.getAllUserCenterKeyByStatusAndCenterKeyOrderByModifyDateDesc("2",centerKey);
        if(ucKeys!=null && ucKeys.size()>0){
            List<BaseUser> baseUsers = baseUserRepository.getLogonUsers(userId,userId,pwd,"1");
            if(baseUsers!=null && baseUsers.size()>0){
                status = 1;
            }else{
                status = 2;
            }
        }
        return status;
    }

    @Override
    public void saveOrUpdateBaseUserRelation(JSONObject params) throws NoSuchAlgorithmException{
        BaseUserRelation baseUserRelation = new BaseUserRelation();
        List<BaseUserRelation> list = baseUserRelationRepository.getBaseUserRelationByCenterKeyAndRemoteUserId(params.getString("centerKey"),params.getString("remoteUserId"));
        if(list!=null && list.size()>0){
            baseUserRelation = list.get(0);
        }else{
            baseUserRelation.setCreateDate(new Date());
            RSAEncrypt.genKeyPair();
            baseUserRelation.setPublicKey(RSAEncrypt.keyMap.get(1));
            baseUserRelation.setPrivateKey(RSAEncrypt.keyMap.get(0));
        }
        baseUserRelation.setModifyDate(new Date());
        baseUserRelation.setLocalUserId(params.getString("localUserId"));
        baseUserRelation.setCenterKey(params.getString("centerKey"));
        baseUserRelation.setOrganId(params.getString("organId"));
        baseUserRelation.setRemoteUserId(params.getString("remoteUserId"));
        baseUserRelation.setUsername(params.getString("username"));
        baseUserRelationRepository.save(baseUserRelation);
    }

    @Override
    public boolean delBaseUserRelationById(String id,String centerKey) {
        List<BaseUserRelation> list = baseUserRelationRepository.findBaseUserRelationByIdAndCenterKey(id,centerKey);
        if(list==null || list.size()==0){
            return false;
        }
        baseUserRelationRepository.delBaseUserRelationById(id);
        return true;
    }

    @Override
    public List<SysPersonnelEntityDTO> loadSysPersonnelByOrganId(String status, String organId) {
        return sysPersonnelRepository.findSysPersonnelByOrganId(status,organId);
    }

    @Override
    public Map<String, Object> loadDetailInfoByOutUserIdAndPwd(String centerKey,String outUserId, String pwd) throws Exception{
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("code",200);
        result.put("msg","获取数据成功");
        List<BaseUserRelation> baseUserRelations = baseUserRelationRepository.getBaseUserRelationByCenterKeyAndRemoteUserId(centerKey,outUserId);
        if(baseUserRelations==null || baseUserRelations.size()==0){
            result.put("msg","密钥错误或账号未绑定中心用户");
            result.put("code",415);
            return result;
        }
        String personId = baseUserRelations.get(0).getLocalUserId();
        List<BaseUser> baseUsers = baseUserRepository.getLogonUsers(personId,personId,pwd,"1");
        if(baseUsers==null || baseUsers.size()==0){
            result.put("msg","密码错误或绑定用户失效");
            result.put("code",415);
            return result;
        }
        List<SysPersonnel> sysPersonnels = sysPersonnelRepository.findSysPersonnelsByPersonIdAndLogOff(personId,"1");
        Map<String,Object> body = new HashMap<String,Object>();
        body.put("personInfo",sysPersonnels!=null?sysPersonnels.get(0):"");
        List<BaseUserRoles> baseUserRolesList = baseUserRolesRepository.getBaseUserRolesByUserIdAndLogoff(baseUsers.get(0).getId(),"1");
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        if(baseUserRolesList!=null && baseUserRolesList.size()>0){
            for (BaseUserRoles role:baseUserRolesList) {
                Map<String,Object> temp = new HashMap<String,Object>();
                temp.put("roles",role);
                List<SysDomain> sysDomains = sysDomainRepository.getSysDomainsByStatusAndId("1",role.getRoleId());
                temp.put("domains",sysDomains);
                List<SysMenus> sysMenuss = sysMenuRepository.getSysMenusByoutRoleIdAndStatus(role.getRoleId(),"1");
                temp.put("menus",sysMenuss);
                list.add(temp);
            }
        }
        body.put("rolesMixInfo",list);
        String data = JSON.toJSONString(body);
        result.put("data",RSAEncrypt.encrypt(data,baseUserRelations.get(0).getPublicKey()));
        return result;
    }

    private List<TreeData> getTreeDataNoChildren(List<TreeDTO> list,String parentId,String parentText){
        if(list==null || list.size()==0){
            return null;
        }
        List<TreeData> datas = new ArrayList<TreeData>();
        List<TreeData> items = new ArrayList<TreeData>();
        Map<String,Object> map= new HashMap<String,Object>();
        if(parentId!=null && !"".equals(parentId)){
            map.put(parentId,parentText);
        }
        for(TreeDTO dto:list){
            Map<String,Object> props=new HashMap<String,Object>();
            props.put("parentId",dto.getParentId()==null?parentId:dto.getParentId());
            props.put("parentText",parentText);
            props.put("type",dto.getType());
            map.put(dto.getId(),dto.getName());
            TreeData treeData = new TreeData(dto.getId(), dto.getName(), props,items);
            datas.add(treeData);
        }
        return datas;
    }
    /***
     * @Description: 组装树形数据，并存放在map中
     * @Author: Vee
     * @Param: list 需要组装的数据
     * @Param: parentId：顶级父级ID
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     * @Date:  2020/1/9
     **/
    private Map<String,Object> getTreeData(List<TreeDTO> list,String parentId,String parentText){
        Map<String,Object> map =new HashMap<String,Object>();
        map.put("msg","查询成功");
        map.put("code","200");
        if(list==null || list.size()==0){
            map.put("totalCount",0);
            map.put("data",null);
        }else{
            List<TreeData> items = new ArrayList<TreeData>();
            map.put("totalCount",list.size());
            List<TreeData> datas = new ArrayList<TreeData>();
            Map<String,Object> organMap= new HashMap<String,Object>();
            if(parentId!=null && !"".equals(parentId)){
                organMap.put(parentId,parentText);
            }
            for(TreeDTO dto:list){
                organMap.put(dto.getId(),dto.getName());
                Map<String,Object> props=new HashMap<String,Object>();
                props.put("parentId",dto.getParentId()==null?parentId:dto.getParentId());
                props.put("parentText",parentText);
                props.put("type",dto.getType());
                TreeData treeData = new TreeData(dto.getId(), dto.getName(), props,items);
                datas.add(treeData);
            }
            List<TreeData> resultTreeData = this.buildTree(datas,organMap,parentId);
            map.put("data",resultTreeData);
        }
        return map;
    }
    /***
     * @Description: 组装
     * @Author: Vee
     * @Param: [treeDatas, map, pid]
     * @return: java.util.List<com.bsoft.entity.TreeData>
     * @Date:  2020/1/9
     **/
    private List<TreeData> buildTree(List<TreeData> treeDatas,Map<String,Object> map, String pid) {
        List<TreeData> treeList = new ArrayList<TreeData>();
        for (TreeData treeData : treeDatas) {
            Map<String, Object> props = new HashMap<String, Object>();
            props = treeData.getProperties();
            if (props.get("parentId").equals(pid)) {
                if(pid!=null && !"".equals(pid)){
                    props.put("parentText",map.get(pid));
                }
                treeData.setItems(buildTree(treeDatas,map,treeData.getKey()));
                treeList.add(treeData);
            }
        }
        return treeList;
    }
}
