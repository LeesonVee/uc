package com.bsoft.service;

import com.alibaba.fastjson.JSONObject;
import com.bsoft.entity.*;
import com.bsoft.message.ModelOperationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Arthur Administrator
 * date 2019/12/27.
 */
public interface UserService {
    public void addTestUser(TestUser testUser);

    public void addUserOrganization(UserOrganization organization);

    public boolean existsById(String id);

    public List<BaseUser> findAll();

    public BaseUser checkLogonUser(String name,String password,String status);

    public SysDomain saveOrUpdateSysDomain(SysDomain sysDomain);

    public UserOrganization saveOrUpdateUserOrganization(UserOrganization userOrganization);

    public SysOffice saveOrUpdateSysOffice(SysOffice sysOffice);

    public List<SysDomain> getSysDomain(String status);


//    public List<SysDomain> querySysDomainByCondition(Map<String,Object> params);

    public Map<String,Object> querySysDomainByCondition(Map<String,Object> params,int pageNo,int pageSize);
    public Map<String, Object> queryUserOrganByCondition(Map<String, Object> params, int pageNo, int pageSize);

    public Map<String,Object> loadOrganTreeData()throws ModelOperationException;

    public Map<String,Object> loadOfficeTreeData(String organId,String organName)throws ModelOperationException;

    public SysOffice loadSysOffice(String id);

    public List<SysRoles> loadSysRoles(String outDomainId);

    public SysRoles saveOrUpdateSysRoles(SysRoles sysRoles);

    public Page<SysMenus> loadSysMenusByPage(Map<String,Object> param,int countParams)throws ModelOperationException;

    public List<SysMenus> loadSysMenusByMenuParentIdAndStatus(String pkey);

    public SysMenus saveOrUpdateSysMenus(SysMenus sysMenus);

    public void updateSysMenus(String subMenuStatus, Date modifyDte, String pkey);

    public Map<String,Object> loadOrganOfficeTreeData(String type,String parentId,String parentText)throws ModelOperationException;

    public List<SysPersonnel> findSysPersonnelByOrganIdOrOfficeId(String type,String id);

    public List<TreeEntityDTO> loadOrganTreeData(String logoff);
    public List<TreeEntityDTO> loadOfficeTreeDataByOrganId(String logoff,String organId);
    public SysPersonnel saveOrUpdateSysPersonnel(SysPersonnel sysPersonnel);

    public List<SysPersonnel> loadSysPersonnels(String filedType,String filedValue,String type,String typeVal) throws ModelOperationException;

    public List<UserMixEntity> loadUserMixDatas(String filedName,String filedValue,String organId);

    public List<BaseUserRolesDTO> loadBaseUserRolesByUserId(String userId);

    public Map<String,Object> loadDomainAndRolesAndOrgan();

    public void saveOrUpdateUserInfo(BaseUser baseUser,List<Map<String,Object>> list);


    public void saveOrUpdateUserCenterKey(UserCenterKey ucKey);


    public List<UserCenterKey> loadAllUserCenterKey(String status);

    public List<UserCenterKey> loadAllUserCenterKeyByContions(String status,String centerKey);

    public List<BaseUserRelationEntityDTO> loadBaseUserRelationByConditions(String type,String organId,String filedValue);

    public void updateBaseUserRelation(String localUserId,String username, Date modifyDate, String id);

    public int vaildCenterKeyAndUesrId(String centerKey,String userId,String pwd);

    public void saveOrUpdateBaseUserRelation(JSONObject params) throws NoSuchAlgorithmException;

    public boolean delBaseUserRelationById(String id,String centerKey);

    public List<SysPersonnelEntityDTO> loadSysPersonnelByOrganId(String status,String organId);

    /**
     * 第三方用户获取对应用户中心详细信息
     * @param centerKey
     * @param outUserId
     * @param pwd
     * @return
     * @throws Exception
     */
    public Map<String,Object> loadDetailInfoByOutUserIdAndPwd(String centerKey,String outUserId,String pwd) throws Exception;

}
