package com.bsoft.service.impl;/**
 * Arthur Administrator
 * date 2019/12/27.
 */

import com.bsoft.entity.*;
import com.bsoft.repository.BaseUserRepository;
import com.bsoft.repository.SysDomainRepository;
import com.bsoft.repository.TestUserRepository;
import com.bsoft.repository.UserOrganizationRepository;
import com.bsoft.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private SysDomainRepository sysDomainRepository;

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
        List<BaseUser> list = baseUserRepository.getLogonUsers(name,password,status);
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
    public List<SysDomain> getSysDomain(String status) {
        return sysDomainRepository.getSysDomains(status);
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

    @Override
    public Map<String, Object> queryUserOrganByCondition(Map<String, Object> params, int pageNo, int pageSize) {
        StringBuffer sql = new StringBuffer(0);
        StringBuffer countSql = new StringBuffer(0);
        countSql.append("select count(1) from USER_ORGANIZATION where ");
        sql.append("SELECT * FROM USER_ORGANIZATION  WHERE ");
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
}
