package com.bsoft.service;

import com.bsoft.entity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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

    public List<SysDomain> getSysDomain(String status);

//    public List<SysDomain> querySysDomainByCondition(Map<String,Object> params);

    public Map<String,Object> querySysDomainByCondition(Map<String,Object> params,int pageNo,int pageSize);
    public Map<String,Object> queryUserOrganByCondition(Map<String,Object> params,int pageNo,int pageSize);

}
