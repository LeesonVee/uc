package com.bsoft.service.impl;/**
 * Arthur Administrator
 * date 2019/12/27.
 */

import com.bsoft.entity.BaseUser;
import com.bsoft.entity.TestUser;
import com.bsoft.entity.UserOrganization;
import com.bsoft.repository.BaseUserRepository;
import com.bsoft.repository.TestUserRepository;
import com.bsoft.repository.UserOrganizationRepository;
import com.bsoft.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *@ClassName UserServiceImpl
 *@Description TODO
 *@Author Administrator
 *@Date 2019/12/27 17:34
 *@Version 1.0
 **/
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private TestUserRepository testUserRepository;
    @Autowired
    private UserOrganizationRepository userOrganizationRepository;

    @Autowired
    private BaseUserRepository baseUserRepository;

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
        List<BaseUser> list = baseUserRepository.getLogonUsers(name,password,status);
        if(list!=null && list.size()>0){
            return list.get(0);
        }
        return null;
    }
}
