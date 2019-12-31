package com.bsoft.service;

import com.bsoft.entity.BaseUser;
import com.bsoft.entity.TestUser;
import com.bsoft.entity.UserOrganization;

import java.util.List;

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
}
